package com.perso.spring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perso.factory.IMediathequeFactory;
import com.perso.manager.movies.MoviesLoader;
import com.perso.model.ILocalMovie;
import com.perso.model.IMachine;
import com.perso.model.IRegexParameter;
import com.perso.model.impl.Parameter;
import com.perso.repository.MovieRepository;
import com.perso.repository.ParametersRepository;

@Service
public class FTPService {

	private Logger logger = LoggerFactory.getLogger( FTPService.class );
	
	@Autowired
	private IMediathequeFactory factory;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ParametersRepository paramRepository;
	
	public List<ILocalMovie> listMovieOnFTPServer(IMachine machine){
		
		FTPClient ftpClient = new FTPClient();
		List<ILocalMovie> movies = new ArrayList<ILocalMovie>();
        ftpClient.setAutodetectUTF8(true);
        
        List<Parameter> paramsInclude = paramRepository.findByName("movie.include");
        List<Parameter> paramsRegex = paramRepository.findByName("movie.regex");
        
        List<IRegexParameter> allRegex = MoviesLoader.generateRegexFromParameter( factory , paramsRegex );
       
        
		try {
            ftpClient.connect(machine.getIp(), machine.getPort());
            showServerReply(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
            	logger.error("Operation failed. Server reply code: " + replyCode);
                return null;
            }
            boolean success = ftpClient.login(machine.getUser(), machine.getPassword());
            showServerReply(ftpClient);
            if (!success) {
            	logger.error("Could not login to the server");
                return null;
            } else {
            	logger.info("LOGGED IN SERVER " + machine.getName() );
            }
            ftpClient.enterLocalPassiveMode();
            logger.info( "Search file in " + machine.getPath() );
            
            movies = getAllFiles(ftpClient , machine.getPath() , paramsInclude, allRegex);

            ftpClient.logout();
            showServerReply(ftpClient);
            ftpClient.disconnect();
            
        } catch (IOException ex) {
            System.out.println("Oops! Something wrong happened");
            ex.printStackTrace();
            return null;
        }
		logger.info("End of FTP process");
		
		/*Filter and rename with parameters in db*/
		return MoviesLoader.findSynchronizedMovies( movieRepository ,  movies );
	}

	private List<ILocalMovie> getAllFiles(FTPClient ftpClient, String directory, List<Parameter> paramInclude, List<IRegexParameter> allRegex) throws IOException{
		
		ArrayList<ILocalMovie> files = new ArrayList<ILocalMovie>();
		FTPFile[] filesInFTP = ftpClient.listFiles( directory );
		if( filesInFTP != null ){
			for( FTPFile ftpFile : filesInFTP ){
				if ( ftpFile.isDirectory() ){
					logger.debug(ftpFile.getName() + " is a directory");
					files.addAll( getAllFiles(ftpClient, directory + "/" + ftpFile.getName() , paramInclude , allRegex ) );
				}else{
					if( isInclude( ftpFile.getName() , paramInclude ) ) {
						logger.debug( "Search info about " + directory + "/" + ftpFile.getName() );
						ILocalMovie movie = factory.createLocalMovie();
						movie.setPath( directory + "/" + ftpFile.getName() );
						movie.setTitle( ftpFile.getName() );
						MoviesLoader.preformateMovieName(movie, allRegex);
						files.add( movie );
						showServerReply(ftpClient);
					}
				}
			}
		}
		return files;
	}
	
	private boolean isInclude(String filename, List<Parameter> paramInclude){
		
		for(Parameter extension : paramInclude ){
			if( filename.endsWith(extension.getValue()) ){
				return true;
			}
		}
		
		return false;
	}
	
	private void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0){
			for( String str : replies){
				logger.debug("FTP Replay : " + str);
			}
		}
	}
	
	
}
