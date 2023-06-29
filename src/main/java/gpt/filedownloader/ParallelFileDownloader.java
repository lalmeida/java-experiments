package gpt.filedownloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ParallelFileDownloader {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * @param args takes one parameter - the path to the file containing the URLs
     *             of the pages/files to be downloaded. There should be one URL pr line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length != 1) {
            throw new IllegalArgumentException("Wrong params!");
        }
        Path urlListFile = Path.of(args[0]);


        try (Stream<String> lines =  Files.lines(urlListFile))
        {
            lines.map(line -> {
                        try {
                            return new URL(line);
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                .forEach(url -> executorService.submit(new DownloaderTask(url)));
        }

        executorService.shutdown();

        // Wait for 60 seconds for downloads to finish
        executorService.awaitTermination(60, TimeUnit.SECONDS);
    }


    private static class DownloaderTask implements Runnable {

        URL url;

        public DownloaderTask(URL url) {
            this.url = url;
        }

        @Override
        public void run() {
            System.out.println("Downloading " + url + " .");
            try {
                InputStream inputStream = url.openStream();
                String[] segments = url.getPath().split("/");
                Files.copy(inputStream, Path.of(segments[segments.length - 1]), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
