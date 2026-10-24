import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvProcessor {

    private static class Employee {
        String id;
        String name;
        String department;
        double salary;

        public Employee(String id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String toCsvRow() {
            return String.join(",", id, name, department, String.valueOf(salary));
        }
    }

    public static void main(String[] args) {
        String inputFile = "input.csv";
        String outputFile = "output_processed.csv";

        List<Employee> validEmployees = new ArrayList<>();
        int totalRows = 0;
        int validRows = 0;
        int invalidRows = 0;

        System.out.println("--- Starting CSV Processing ---");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String header = br.readLine();
            if (header == null) {
                System.out.println("The input file is empty.");
                return;
            }

            String line;
            while ((line = br.readLine()) != null) {
                totalRows++;
                try {
                    Employee emp = parseAndValidate(line);
                    validEmployees.add(emp);
                    validRows++;
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping malformed row [" + line + "] -> Reason: " + e.getMessage());
                    invalidRows++;
                }
            }
        } catch (IOException e) {
            System.err.println("Critical error reading input file: " + e.getMessage());
            return;
        }

        double totalSalary = 0;
        Map<String, Integer> deptCounts = new HashMap<>();

        for (Employee emp : validEmployees) {
            totalSalary += emp.salary;
            deptCounts.put(emp.department, deptCounts.getOrDefault(emp.department, 0) + 1);
        }
        double averageSalary = validRows > 0 ? (totalSalary / validRows) : 0;

        System.out.println("\n--- Processing Summary ---");
        System.out.println("Total Rows Evaluated: " + totalRows);
        System.out.println("Successful Records:   " + validRows);
        System.out.println("Skipped/Invalid Rows: " + invalidRows);
        System.out.printf("Overall Average Salary: $%.2f%n", averageSalary);
        System.out.println("Department Counts:    " + deptCounts);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("id,name,department,salary\n");
            
            for (Employee emp : validEmployees) {
                bw.write(emp.toCsvRow() + "\n");
            }
            
            System.out.println("\nSuccessfully generated clean output file at: " + outputFile);
        } catch (IOException e) {
            System.err.println("Critical error writing to output file: " + e.getMessage());
        }
    }

    private static Employee parseAndValidate(String line) throws IllegalArgumentException {
        // Handle empty lines gracefully
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Line is completely empty.");
        }

        String[] tokens = line.split(",", -1);

        // FIX: Ensure exact mapping to prevent out-of-bounds or misaligned structural errors
        if (tokens.length < 4) {
            throw new IllegalArgumentException("Missing columns. Expected 4 fields, found " + tokens.length);
        }

        String id = tokens[0].trim();
        String name = tokens[1].trim();
        String department = tokens[2].trim();
        String salaryStr = tokens[3].trim();

        if (id.isEmpty() || name.isEmpty() || department.isEmpty() || salaryStr.isEmpty()) {
            throw new IllegalArgumentException("One or more fields are empty/missing.");
        }

        double salary;
        try {
            salary = Double.parseDouble(salaryStr);
            if (salary < 0) {
                throw new IllegalArgumentException("Salary cannot be a negative value.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Salary field contains an invalid numeric format: '" + salaryStr + "'");
        }

        return new Employee(id, name, department, salary);
    }
}
