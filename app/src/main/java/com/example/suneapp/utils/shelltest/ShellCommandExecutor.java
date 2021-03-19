package com.example.suneapp.utils.shelltest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShellCommandExecutor {
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_EXIT = "exit\n";
    private static final boolean ISDEBUG = true;

    public static List<String> execute(String command) {
        return execute(new String[]{command});
    }

    public static List<String> execute(String[] commands) {
        // Initialisation des variables
        List<String> results = new ArrayList<String>();
        int status = -1;
        if (commands == null || commands.length == 0) {
            return null;
        }
        //System.out.println("execute command start : " + commands);
        Process process = null;
        BufferedReader successReader = null;
        BufferedReader errorReader = null;
        StringBuilder errorMsg = null;

        DataOutputStream dos = null;
        try {
            // Création d'un contexte d'exécution standard avec la commande sh
            // Si on exécutais la commande tout de suite on serait dans un contexte
            // limité d'android et on aurait pas toutes les commandes
            process = Runtime.getRuntime().exec(COMMAND_SH);
            // Récupération du flux de sortie du contexte standard
            dos = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) {
                    continue;
                }
                // Exécution de la commande dans le contexte standard
                dos.write(command.getBytes());
                dos.writeBytes(COMMAND_LINE_END);
                dos.flush();
            }
            // Sortie du contexte d'exécution à l'aide de la commande exit
            dos.writeBytes(COMMAND_EXIT);
            dos.flush();

            // Attente de la fin du processus d'exécution de la commande
            status = process.waitFor();

            // Lecture du message de retour en mode erreur si erreur ou en mode succès si succès
            errorMsg = new StringBuilder();
            successReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String lineStr;
            while ((lineStr = successReader.readLine()) != null) {
                results.add(lineStr);
                //System.out.println(lineStr);
            }
            while ((lineStr = errorReader.readLine()) != null) {
                errorMsg.append(lineStr);
            }
            // gestion des erreurs d'IO qui auraient pu avoir lieu à l'exécution
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // fermeture de l'IO de l'exécution et de lecture des résultats
                if (dos != null) {
                    dos.close();
                }
                if (successReader != null) {
                    successReader.close();
                }
                if (errorReader != null) {
                    errorReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (process != null) {
                process.destroy();
            }
        }
        //System.out.println(String.format(Locale.FRANCE, "execute command end,errorMsg:%s,and status %d: ", errorMsg, status));
        return results;
    }
}
