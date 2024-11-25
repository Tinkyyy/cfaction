package eu.tinky.cfaction.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Argument {
    private final List<String> identifiers;
    private String value;
    private final boolean optional;
    private final List<Argument> optionalArguments;

    public Argument(List<String> identifiers, boolean optional, List<Argument> optionalArguments) {
        this.identifiers = identifiers;
        this.optional = optional;
        this.optionalArguments = optionalArguments;
    }

    public Argument(List<String> identifiers, boolean optional) {
        this(identifiers, optional, new ArrayList<>());
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

    public boolean isOptional() {
        return optional;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String joinedIdentifiers = String.join("/", this.identifiers);

        if (this.optional) {
            String optionalArgs = optionalArguments.stream()
                    .map(Argument::toString)
                    .collect(Collectors.joining(" "));

            if (this.identifiers.isEmpty()) {
                return "[" + optionalArgs + "]";
            }

            return "[" + joinedIdentifiers + "]";
        }
        return "<" + joinedIdentifiers + ">";
    }
}