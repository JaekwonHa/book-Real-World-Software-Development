package chapter_02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BankStatementCSVParser implements BankStatementParser {
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public BankTransaction parseFrom(String line) {
        final String[] colums = line.split(",");

        final LocalDate date = LocalDate.parse(colums[0], DATE_PATTERN);
        final double amount = Double.parseDouble(colums[1]);

        return new BankTransaction(date, amount, colums[2]);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines) {
        return lines.stream().map(this::parseFrom).collect(toList());
    }
}
