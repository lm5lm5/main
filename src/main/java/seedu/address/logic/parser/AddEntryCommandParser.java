package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENTRYLOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENTRYNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENTRYTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENTRYCALORIE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.Entry;

/**
 * Parses input arguments and creates a new Add EntryCommand object
 */
public class AddEntryCommandParser implements Parser<AddEntryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEntryCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ENTRYNAME, PREFIX_ENTRYTIME,
                        PREFIX_ENTRYLOCATION, PREFIX_ENTRYCALORIE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ENTRYNAME, PREFIX_ENTRYTIME, PREFIX_ENTRYLOCATION, PREFIX_ENTRYCALORIE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEntryCommand.MESSAGE_USAGE));
        }

        String name = ParserUtil.parseEntryName(argMultimap.getValue(PREFIX_ENTRYNAME).get());
        String time = ParserUtil.parseEntryTime(argMultimap.getValue(PREFIX_ENTRYTIME).get());
        String location = ParserUtil.parseEntryLocation(argMultimap.getValue(PREFIX_ENTRYLOCATION).get());
        String calorie = ParserUtil.parseEntryCalorie(argMultimap.getValue(PREFIX_ENTRYCALORIE).get());

        Entry Entry = new Entry(name, time, location, calorie);

        return new AddEntryCommand(Entry);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
