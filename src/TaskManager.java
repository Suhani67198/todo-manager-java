import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;
    private final Path file = Paths.get("tasks.txt");

    public TaskManager() {
        load();
    }

    public void addTask(String title) {
        tasks.add(new Task(nextId++, title, false));
        save();
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }
        for (Task t : tasks) {
            System.out.println(t);
        }
    }

    public boolean markDone(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setDone(true);
                save();
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id) {
        boolean removed = tasks.removeIf(t -> t.getId() == id);
        if (removed) save();
        return removed;
    }

    private void save() {
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(t.getId() + "|" + t.isDone() + "|" + t.getTitle());
        }
        try {
            Files.write(file, lines);
        } catch (IOException e) {
            System.out.println("Could not save tasks: " + e.getMessage());
        }
    }

    private void load() {
        if (!Files.exists(file)) return;
        try {
            for (String line : Files.readAllLines(file)) {
                String[] parts = line.split("\\|", 3);
                int id = Integer.parseInt(parts[0]);
                boolean done = Boolean.parseBoolean(parts[1]);
                tasks.add(new Task(id, parts[2], done));
                nextId = Math.max(nextId, id + 1);
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("Could not load tasks: " + e.getMessage());
        }
    }
}