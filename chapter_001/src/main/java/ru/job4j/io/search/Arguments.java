package ru.job4j.io.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Arguments {
    private String[] args;
    private final HashMap<String, String> values = new HashMap<>();
    private SearchType searchBy;
    private final List<String> searchFlags = List.of("m", "f", "r");
    private final List<String> monoFlags = List.of("-m", "-f", "-r");

    public Arguments(String[] args) {
        this.args = args;
        parse();
    }

    private void valid() {
        for (String arg : args) {
            if (!isMonoFlag(arg) && (!arg.startsWith("-") || !arg.contains("="))) {
                throw new IllegalArgumentException();
            }
        }
    }

    public String directory() {
        return values.get(ParameterWithValues.DIRECTORY.key());
    }

    public String output() {
        return values.get(ParameterWithValues.OUTPUT.key());
    }

    public String name() {
        return values.get(ParameterWithValues.NAME.key());
    }

    public SearchType searchBy() {
        return searchBy;
    }

    public enum SearchType {
        RegexType,
        MaskType,
        FullMatchType
    }


    private void addArgs(String s) {
        String[] parts = s.split("=");
        String flag = parts[0].substring(1);
        if (isSearchFlag(flag)) {
            searchBy = getType(flag);
            values.put(flag,null);
            return;
        }
        values.put(flag, parts[1]);
    }

    private boolean isSearchFlag(String s) {
        return searchFlags.contains(s);
    }

    private boolean isMonoFlag(String s) {
        return monoFlags.contains(s);
    }

    private void parse() {
        valid();
        Arrays.stream(args)
                .forEach(this::addArgs);
        checkParametersWithValues();
        checkSearchTypeParameters();
    }

    private void checkSearchTypeParameters() {
        if (!(values.containsKey(SearchTypeParameter.FULL_MATCH.key())
                || values.containsKey(SearchTypeParameter.MASK.key())
                || values.containsKey(SearchTypeParameter.REGEX.key()))) {
            throw new RuntimeException("Search type parameter is not defined");
        }
    }

    private void checkParametersWithValues() {
        if (ParameterWithValues.DIRECTORY.isMandatory() && !values.containsKey(ParameterWithValues.DIRECTORY.key())) {
            throw new RuntimeException("Directory is not defined");
        }
        if (ParameterWithValues.OUTPUT.isMandatory() && !values.containsKey(ParameterWithValues.OUTPUT.key())) {
            throw new RuntimeException("Output is not defined");
        }
        if (ParameterWithValues.NAME.isMandatory() && !values.containsKey(ParameterWithValues.NAME.key())) {
            throw new RuntimeException("Name is not defined");
        }
    }

    private static SearchType getType(String flag) {
        if (SearchTypeParameter.REGEX.key().equals(flag)) {
            return SearchType.RegexType;
        } else if (SearchTypeParameter.MASK.key().equals(flag)) {
            return SearchType.MaskType;
        } else if (SearchTypeParameter.FULL_MATCH.key.equals(flag)) {
            return SearchType.FullMatchType;
        } else {
            throw new IllegalArgumentException("Unknown type");
        }
    }

    private enum ParameterWithValues {
        DIRECTORY("d", true),
        NAME("n", true),
        OUTPUT("o", true);

        private final String key;
        private final boolean mandatory;

        ParameterWithValues(String parameter, boolean mandatory) {
            this.key = parameter;
            this.mandatory = mandatory;
        }

        public String key() {
            return key;
        }

        public boolean isMandatory() {
            return mandatory;
        }
    }

    private enum SearchTypeParameter {
        MASK("m"),
        FULL_MATCH("f"),
        REGEX("r");

        private final String key;

        SearchTypeParameter(String key) {
            this.key = key;
        }

        public String key() {
            return key;
        }
    }
}
