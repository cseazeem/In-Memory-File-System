package org.example;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InMemoryFileSystem {
    private String currentDirectory;
    private Map<String, Object> fileSystem;

    public InMemoryFileSystem() {
        currentDirectory = "/";
        fileSystem = new HashMap<>();
    }

    public void mkdir(String directoryName) {
        String newDirectoryPath = currentDirectory + "/" + directoryName;
        if (!fileSystem.containsKey(newDirectoryPath)) {
            fileSystem.put(newDirectoryPath, new HashMap<String, Object>());
        } else {
            System.out.println("Directory '" + directoryName + "' already exists.");
        }
    }

    public void cd(String path) {
        if (path.equals("/")) {
            currentDirectory = "/";
        } else if (path.equals("..")) {
            if (!currentDirectory.equals("/")) {
                currentDirectory = currentDirectory.substring(0, currentDirectory.lastIndexOf('/'));
            }
        } else {
            String newPath = currentDirectory + "/" + path;
            if (fileSystem.containsKey(newPath) && fileSystem.get(newPath) instanceof Map) {
                currentDirectory = newPath;
            } else {
                System.out.println("Invalid path: '" + path + "'");
            }
        }
    }

    public void ls() {
        ls(currentDirectory);
    }

    public void ls(String path) {
        if (fileSystem.containsKey(path) && fileSystem.get(path) instanceof Map) {
            Map<String, Object> contents = (Map<String, Object>) fileSystem.get(path);
            System.out.println("Contents of " + path + ": " + contents.keySet());
        } else {
            System.out.println("Invalid path: '" + path + "'");
        }
    }

    public void touch(String fileName) {
        String filePath = currentDirectory + "/" + fileName;
        if (!fileSystem.containsKey(filePath)) {
            fileSystem.put(filePath, "");
        } else {
            System.out.println("File '" + fileName + "' already exists.");
        }
    }

    public void echo(String text, String fileName) {
        String filePath = currentDirectory + "/" + fileName;
        if (fileSystem.containsKey(filePath)) {
            fileSystem.put(filePath, text);
        } else {
            System.out.println("File '" + fileName + "' does not exist.");
        }
    }

    public void cat(String fileName) {
        String filePath = currentDirectory + "/" + fileName;
        if (fileSystem.containsKey(filePath)) {
            System.out.println(fileSystem.get(filePath));
        } else {
            System.out.println("File '" + fileName + "' does not exist.");
        }
    }

    public void mv(String source, String destination) {
        String sourcePath = currentDirectory + "/" + source;
        String destPath = currentDirectory + "/" + destination;
        if (fileSystem.containsKey(sourcePath)) {
            if (!fileSystem.containsKey(destPath)) {
                fileSystem.put(destPath, fileSystem.remove(sourcePath));
            } else {
                System.out.println("Destination '" + destination + "' already exists.");
            }
        } else {
            System.out.println("Source '" + source + "' does not exist.");
        }
    }

    public void cp(String source, String destination) {
        String sourcePath = currentDirectory + "/" + source;
        String destPath = currentDirectory + "/" + destination;
        if (fileSystem.containsKey(sourcePath)) {
            if (!fileSystem.containsKey(destPath)) {
                fileSystem.put(destPath, fileSystem.get(sourcePath));
            } else {
                System.out.println("Destination '" + destination + "' already exists.");
            }
        } else {
            System.out.println("Source '" + source + "' does not exist.");
        }
    }

    public void rm(String path) {
        String filePath = currentDirectory + "/" + path;
        if (fileSystem.containsKey(filePath)) {
            if (fileSystem.get(filePath) instanceof Map && !((Map<String, Object>) fileSystem.get(filePath)).isEmpty()) {
                System.out.println("Error: Cannot remove non-empty directory '" + path + "'.");
            } else {
                fileSystem.remove(filePath);
            }
        } else {
            System.out.println("File or directory '" + path + "' does not exist.");
        }
    }

    public static void main(String[] args) {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(fileSystem.currentDirectory + " $ ");
            String command = scanner.nextLine();

            if (command.startsWith("mkdir")) {
                String[] tokens = command.split(" ");
                if (tokens.length == 2) {
                    fileSystem.mkdir(tokens[1]);
                } else {
                    System.out.println("Invalid command. Usage: mkdir <directory_name>");
                }
            } else if (command.startsWith("cd")) {
                String[] tokens = command.split(" ");
                if (tokens.length == 2) {
                    fileSystem.cd(tokens[1]);
                } else {
                    System.out.println("Invalid command. Usage: cd <directory_path>");
                }
            } else if (command.equals("ls")) {
                fileSystem.ls();
            } else if (command.startsWith("ls")) {
                String[] tokens = command.split(" ");
                if (tokens.length == 2) {
                    fileSystem.ls(tokens[1]);
                } else {
                    System.out.println("Invalid command. Usage: ls <directory_path>");
                }
            } else if (command.startsWith("touch")) {
                String[] tokens = command.split(" ");
                if (tokens.length == 2) {
                    fileSystem.touch(tokens[1]);
                } else {
                    System.out.println("Invalid command. Usage: touch <file_name>");
                }
            } else if (command.startsWith("echo")) {
                String[] tokens = command.split(" ", 3);
                if (tokens.length == 3) {
                    fileSystem.echo(tokens[2], tokens[1]);
                } else {
                    System.out.println("Invalid command. Usage: echo '<text>' > <file_name>");
                }
            } else if (command.startsWith("cat")) {
                String[] tokens = command.split(" ");
                if (tokens.length == 2) {
                    fileSystem.cat(tokens[1]);
                } else {
                    System.out.println("Invalid command. Usage: cat <file_name>");
                }
            } else if (command.startsWith("mv")) {
                String[] tokens = command.split(" ");
                if (tokens.length == 3) {
                    fileSystem.mv(tokens[1], tokens[2]);
                } else {
                    System.out.println("Invalid command. Usage: mv <source> <destination>");
                }
            } else if (command.startsWith("cp")) {
                String[] tokens = command.split(" ");
                if (tokens.length == 3) {
                    fileSystem.cp(tokens[1], tokens[2]);
                } else {
                    System.out.println("Invalid command. Usage: cp <source> <destination>");
                }
            } else if (command.startsWith("rm")) {
                String[] tokens = command.split(" ");
                if (tokens.length == 2) {
                    fileSystem.rm(tokens[1]);
                } else {
                    System.out.println("Invalid command. Usage: rm <path>");
                }
            } else if (command.equals("exit")) {
                System.out.println("Exiting file system.");
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
    }
}
