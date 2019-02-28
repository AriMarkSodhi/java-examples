package com.ari.concurrent;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class AsyncTask {

    private final int duration;

    public AsyncTask(int duration) {
        this.duration = duration;
    }

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
            int count = 1;

            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "custom-executor-" + count++);
            }
        });

        try {
            CompletableFuture.runAsync(() -> System.out.println("Run async in completable future " + Thread.currentThread()), executorService);
            CompletableFuture.supplyAsync(() -> {
                System.out.println("Run async in completable future " + Thread.currentThread());
                return 5;
            }, executorService).thenRunAsync(() -> {
                System.out.println("Run async in completable future " + Thread.currentThread());
            }, executorService);

            List<AsyncTask> tasks = new ArrayList<AsyncTask>();
            for (int i = 0; i < 5; i++) {
                tasks.add(new AsyncTask(1));
            }

            long start = System.nanoTime();
            List<CompletableFuture<Integer>> futures =
                    tasks.stream()
                            .map(t -> CompletableFuture.supplyAsync(() -> t.calculate(), executorService))
                            .collect(Collectors.toList());

            List<Integer> result =
                    futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList());
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
            System.out.println(result);


            recursiveListDir(Paths.get("/Users/asodhi/Desktop"));

            CompletableFuture<String> future = CompletableFuture.supplyAsync(AsyncTask::getFileName, executorService)
                    .thenApplyAsync(AsyncTask::readFileContents, executorService).handle((res, ex) -> {
                        if (ex != null) {
                            ex.printStackTrace();
                            System.out.println("Oops! We have an exception - " + ex.getMessage());
                            return "Unknown!";
                        }
                        return res;
                    });

            future.get(); // block for result to prevent threadpool from shutting down

            // .thenAcceptAsync(AsyncTask::writeLanBlocking, executorService)
            // .thenRunAsync(AsyncTask::notifyUser, executorService);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (executorService != null) executorService.shutdown();
        }
    }

    private static String readFileContents(Path fileName) {
        try {
            System.out.println(Thread.currentThread().getName() + " about to read:" + fileName);
            AsynchronousFileChannel ch = AsynchronousFileChannel.open(fileName, StandardOpenOption.READ);

//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//            long position = 0;
//
//            Future<Integer> operation = ch.read(buffer, position);
//
//            while (!operation.isDone()) ;
//
//            buffer.flip();
//            byte[] data = new byte[buffer.limit()];
//            buffer.get(data);
//            System.out.println("Data:"+new String(data));
//            buffer.clear();


            ByteBuffer buffer = ByteBuffer.allocate(1024);

            ch.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("Bytes read [" + result + "]");
                    for (int i = 0; i < attachment.limit(); i++) {
                        System.out.println((char) attachment.get(i));
                    }
//                    attachment.flip();
//                    byte[] data = new byte[attachment.limit()];
//                    attachment.get(data);
//                    System.out.println("Result="+new String(data));
//                    attachment.clear();
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "foo";
    }

    private static void recursiveListDir(Path dir)
    {
        DirectoryStream<Path> directoryStream = null;
        try {
            directoryStream = Files.newDirectoryStream(dir);

            for (Path dirPath : directoryStream) {
                System.out.println(dirPath.toString());
                if (Files.isDirectory(dirPath))
                {
                    recursiveListDir(dirPath);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                directoryStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private static Path getFileName() {
        //CompletableFuture<String> cf = new CompletableFuture<>();

        Path path = Paths.get("/Users/asodhi/Desktop/test.txt");
        if (!Files.exists(path,
                new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
            throw new RuntimeException("File does not exist." + path.toString());
        }

        if (path == null) {
            throw new RuntimeException("Could not get file path.");
        }
        Path filePath = path.getFileName();
        if (filePath == null) {
            throw new RuntimeException("Could not get file path.");
        }
        String fileName = filePath.toString();
        System.out.println(Thread.currentThread().getName() + " Dir=" + path.getParent() + " File=" + fileName);
        return path;
    }

    public int calculate() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(duration * 1000);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        return duration;
    }


}
