package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> answerList = null;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (var question = new BufferedReader(new InputStreamReader(System.in))) {
            String userQuest = question.readLine();
            String botAnswer;
            List<String> chatLogs = new ArrayList<>();
            boolean stop = false;
            while (!userQuest.equals(OUT)) {
                chatLogs.add(userQuest);
                if (userQuest.equals(STOP)) {
                    stop = true;
                }
                if (userQuest.equals(CONTINUE)) {
                    stop = false;
                }
                if (!stop) {
                    botAnswer = getBotAnswers(botAnswers);
                    chatLogs.add(botAnswer);
                    System.out.println(botAnswer);
                }
                userQuest = question.readLine();
            }
            writeLog(chatLogs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeLog(List<String> chatLogs) {
        try (var ins = new BufferedWriter(new FileWriter(path))) {
            for (var log : chatLogs) {
                ins.write(log);
                ins.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBotAnswers(String botAnswers) {
        if (answerList == null) {
            try (var getAnswer = new BufferedReader(new FileReader(botAnswers))) {
                answerList = getAnswer.lines().collect(Collectors.toList());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Random random = new Random();
        return answerList.get(random.nextInt(answerList.size()));
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("", "");
        cc.run();
    }
}