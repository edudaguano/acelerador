package com.br.accava.acelerador.angular.application.controller;

import com.br.accava.acelerador.angular.AcceleratorRunner;
import com.br.accava.acelerador.angular.core.model.LanguageType;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AngularAccelerator {


    private static final String MSG_ENTER = "Entre com o caminho dos arquivos front";
    private static final String MSG_ASK_LANGUAGE_PAGE = "Quais linguagens podem conter em uma página?Separe por virgula (EX: asp,javascript)";
    private static final String MSG_EXIT = "Bye Bye";
    private static final String MSG_INITIALS = "Escreva o nome da sigla";

    private static String path;

    private static List<LanguageType> pageLanguages;

    //--------------------------------------------------------------------------------
    private final static Logger console = Logger.getLogger(AngularAccelerator.class.getName());

    public static void main(String[] args) {
        runApplication();
    }
    private static void runApplication() {

        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("\n\n-------------------------------------------------------------\n");
                System.out.println(MSG_ENTER);

                path = br.readLine();

                if ("exit".equals(path)) {
                    runExit();
                    break;
                }

                System.out.println(MSG_ASK_LANGUAGE_PAGE);

                pageLanguages = new ArrayList<>();

                Arrays.asList(br.readLine().split(",")).forEach(language -> {
                    String[]languageInfo = language.split(":");
                    System.out.println(languageInfo[0] + " " + languageInfo[1]);
                    pageLanguages.add(new LanguageType(languageInfo[0],languageInfo[1]));
                });


                callService();

                System.out.println("\n\n-------------------------------------------------------------\n");
            }

        } catch (IOException e) {
            console.log(Level.SEVERE, "Erro ao ler o input", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void callService(){
        AcceleratorRunner acceleratorRunner = new AcceleratorRunner(path,pageLanguages);
        acceleratorRunner.run();
    }

    private static void runExit() {
        System.out.println(MSG_EXIT);
        Executors.newSingleThreadScheduledExecutor().schedule(() -> System.exit(0), 3, TimeUnit.SECONDS);
    }

    }




