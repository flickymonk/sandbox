package com.alevel.csvparser;

import java.util.List;

public interface CSVMapper {

    <T> List<T> map(CSVTable table, Class<T> type) throws CSVMappingException;

}
