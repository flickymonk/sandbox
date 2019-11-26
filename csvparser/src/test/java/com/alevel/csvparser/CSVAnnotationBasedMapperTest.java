package com.alevel.csvparser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVAnnotationBasedMapperTest {

    private CSVAnnotationBasedMapper mapper;

    private CSVFileParser parser;

    @BeforeEach
    void setUp() {
        parser = new CSVFileParser();
        mapper = new CSVAnnotationBasedMapper();
    }

    @Test
    void shouldMapTableToObjectList() throws Exception {
        Path source = Paths.get(getClass().getResource("/example1.csv").toURI());
        CSVTable table = parser.parse(source);
        List<PersonalData> personalData = mapper.map(table, PersonalData.class);

        assertEquals(2, personalData.size());

        PersonalData person1 = personalData.get(0);
        assertEquals("Mike", person1.getName());
        assertEquals(27, person1.getAge());
        assertEquals(PersonalData.Gender.MALE, person1.getGender());
        assertEquals("janitor", person1.getOccupation());

        PersonalData person2 = personalData.get(1);
        assertEquals("Beth", person2.getName());
        assertEquals(23, person2.getAge());
        assertEquals(PersonalData.Gender.FEMALE, person2.getGender());
        assertEquals("recruiter", person2.getOccupation());
    }
}