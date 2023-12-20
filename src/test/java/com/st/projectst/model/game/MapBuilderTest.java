package com.st.projectst.model.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapBuilderTest {

    private MapBuilder mapBuilder;
    private List<String> linesMap;

    @BeforeEach
    public void setUp() throws IOException {
        String testMap =
                "MWPPPPPPP\n" +
                        "WPXPPPPPW\n" +
                        "WPWMMMMMW\n" +
                        "WWWWWWWWW";

        BufferedReader reader = new BufferedReader(new StringReader(testMap));
        linesMap = new ArrayList<>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                linesMap.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mapBuilder = new MapBuilder(1);
    }

    @Test
    public void testMapBuilder() {
        int level = 1;
        try {
            MapBuilder mapBuilder = new MapBuilder(level);
            Map map = mapBuilder.buildMap();

            assertNotNull(map);
            assertEquals(map.getWidth(), 320);
            assertEquals(map.getHeight(),61);

        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testLoadFromFile() {
        String filePath = "src/main/resources/levels/map1.txt";

        try (BufferedReader buff = new BufferedReader(new FileReader(filePath))) {
            MapBuilder mapBuilder = new MapBuilder(1);
            List<String> lines = mapBuilder.loadFromFile(buff);

            assertNotNull(lines);
            assertFalse(lines.isEmpty());

        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    
}
