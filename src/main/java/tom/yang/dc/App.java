package tom.yang.dc;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(final String[] args) throws ParseException, IOException {
        final Options options = new Options();
        final Option t = Option.builder("T").hasArg()
                .required(true)
                .longOpt("arg-name")
                .build();
        options.addOption(t);
        final Option dir = Option.builder("D").hasArg().required()
                .desc("the dir to run the clear program").build();
        options.addOption(dir);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine line = parser.parse(options, args);
        final int days = Integer.valueOf(line.getOptionValue("T"));
        final File f = new File(line.getOptionValue("D"));
        if (f.isDirectory() && f.exists()) {
            File[] files = f.listFiles();
            for(File file : files)
                deleteFileOrFolder(file.toPath(), days);
        } else {
            System.err.println("dir path error.");
        }
    }
    
    static boolean oldEnough(File f,int days){
        final long delta = days * 24 * 3600 * 1000;
        final long currTime = System.currentTimeMillis();
        return currTime - f.lastModified() > delta;
    }
    
    public static void deleteFileOrFolder(final Path path,final int days) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
          @Override public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
            throws IOException {
              handle(file,days);
            return FileVisitResult.CONTINUE;
          }

          @Override public FileVisitResult visitFileFailed(final Path file, final IOException e) {
            return handleException(e);
          }

          private FileVisitResult handleException(final IOException e) {
            e.printStackTrace();
            return FileVisitResult.TERMINATE;
          }
          
          private void handle(Path file, int days) throws IOException{
              if(oldEnough(file.toFile(), days)){
                  System.out.println("delete file : " + file.getFileName());
                  Files.delete(file);
              }
          }

          @Override public FileVisitResult postVisitDirectory(final Path dir, final IOException e)
            throws IOException {
            handle(dir,days);
            return FileVisitResult.CONTINUE;
          }
        });
      };
}
