package com.alevel.sandbox.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class FileChannelDemo {
    public static void main(String[] args) {
        String newData = "New String to write to file..." + System.currentTimeMillis();

        Path path = Paths.get("misc/files/channel.txt");
        ByteBuffer buffer = ByteBuffer.wrap(newData.getBytes(StandardCharsets.UTF_8));
//        ByteBuffer buffer = ByteBuffer.allocate(48);
//        buffer.put(newData.getBytes(StandardCharsets.UTF_8));
//        buffer.flip();

        try(FileChannel channel = FileChannel.open(path, WRITE, CREATE)) {
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        buffer.clear();
        try(FileChannel channel = FileChannel.open(path, READ)) {
            channel.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }
}
