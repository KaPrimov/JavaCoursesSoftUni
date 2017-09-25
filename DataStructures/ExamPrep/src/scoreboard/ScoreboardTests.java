package scoreboard;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ScoreboardTests {

    private void executeTest(String inputFileName, String outputFileName) throws IOException {
        CommandExecutor commandExecutor = new CommandExecutor();

        Path inputPath = Paths.get("src\\scoreboard\\Judge-Tests\\" + inputFileName);
        List<String> inputCommands = Files.readAllLines(inputPath);
        StringBuilder output = new StringBuilder();
        for (String command : inputCommands) {
            if (command.equals("End")) {
                break;
            }

            if (!command.equals("End")) {
                String commandOutput = commandExecutor.processCommand(command);
                output.append(commandOutput).append(System.lineSeparator());
            }
        }

        Path outputPath = Paths.get("src\\scoreboard\\Judge-Tests\\" + outputFileName);
        String expectedOutput = String.join(System.lineSeparator(), Files.readAllLines(outputPath));
        String actualOutput = output.toString().trim();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test(timeout=200)
    public void Test000_SampleInput() throws IOException {
        executeTest("test.000.001.in.txt", "test.000.001.out.txt");
    }



    @Test(timeout=200)
    public void Test001_RegisterUser() throws IOException {
        executeTest("test.001.in.txt", "test.001.out.txt");
    }

    @Test(timeout=200)
    public void Test002_RegisterGame() throws IOException {
        executeTest("test.002.in.txt", "test.002.out.txt");
    }

    @Test(timeout=200)
    public void Test003_RegisterUser_RegisterGame_AddScore_Simple() throws IOException {
        executeTest("test.003.in.txt", "test.003.out.txt");
    }

    @Test(timeout=200)
    public void Test004_RegisterUser_RegisterGame_AddScore_Complex() throws IOException {
        executeTest("test.004.in.txt", "test.004.out.txt");
    }

    @Test(timeout=200)
    public void Test005_ShowScoreboard_Very_Simple() throws IOException {
        executeTest("test.005.in.txt", "test.005.out.txt");
    }

    @Test(timeout=200)
    public void Test006_ShowScoreboard_Simple() throws IOException {
        executeTest("test.006.in.txt", "test.006.out.txt");
    }

    @Test(timeout=200)
    public void Test007_ShowScoreboard_Empty() throws IOException {
        executeTest("test.007.in.txt", "test.007.out.txt");
    }

    @Test(timeout=200)
    public void Test008_ShowScoreboard_All_Cases() throws IOException {
        executeTest("test.008.in.txt", "test.008.out.txt");
    }

    @Test(timeout=200)
    public void Test009_ListGamesByPrefix() throws IOException {
        executeTest("test.009.in.txt", "test.009.out.txt");
    }

    @Test(timeout=200)
    public void Test010_RegisterGame_DeleteGame() throws IOException {
        executeTest("test.010.in.txt", "test.010.out.txt");
    }

    @Test(timeout=200)
    public void Test011_AddScore_DeleteGame() throws IOException {
        executeTest("test.011.in.txt", "test.011.out.txt");
    }

    @Test(timeout=200)
    public void Test012_DeleteGame_WithInvalidCases() throws IOException {
        executeTest("test.012.in.txt", "test.012.out.txt");
    }

    @Test(timeout=200)
    public void Test013_ShowScoreboard_DeleteGame_Complex() throws IOException {
        executeTest("test.013.in.txt", "test.013.out.txt");
    }

    @Test(timeout=200)
    public void Test014_ListGamesByPrefix_DeleteGame() throws IOException {
        executeTest("test.014.in.txt", "test.014.out.txt");
    }

    @Test(timeout=200)
    public void Test015_All_Commands_All_Cases() throws IOException {
        executeTest("test.015.in.txt", "test.015.out.txt");
    }

    @Test(timeout=200)
    public void Test016_Performance_RegisterUser() throws IOException {
        executeTest("test.016.in.txt", "test.016.out.txt");
    }

    @Test(timeout=200)
    public void Test017_Performance_RegisterGame() throws IOException {
        executeTest("test.017.in.txt", "test.017.out.txt");
    }

    @Test(timeout=200)
    public void Test018_Performance_AddScore() throws IOException {
        executeTest("test.018.in.txt", "test.018.out.txt");
    }

    @Test(timeout=200)
    public void Test019_Performance_ShowScoreboard() throws IOException {
        executeTest("test.019.in.txt", "test.019.out.txt");
    }

    @Test(timeout=200)
    public void Test020_Performance_ListGamesByPrefix() throws IOException {
        executeTest("test.020.in.txt", "test.020.out.txt");
    }

    @Test(timeout=200)
    public void Test021_Performance_DeleteGame() throws IOException {
        executeTest("test.021.in.txt", "test.021.out.txt");
    }

    @Test(timeout=200)
    public void Test022_Performance_AllCommands() throws IOException {
        executeTest("test.022.in.txt", "test.022.out.txt");
    }
}
