package com.karthik.springbootgraphql.datafetchers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.netflix.dgs.codgen.generated.types.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

@DgsComponent
public class ShowDataFetcher {

    private List<Show> showData = List.of(Show.newBuilder().title("DareDevil").releaseYear(2016).build(),
            Show.newBuilder().title("Counter").releaseYear(2020).build());

    @DgsData(parentType = "Query", field = "shows")
    // @DgsQuery is a shorthand for above
    public List<Show> shows(@InputArgument String titleFilter) {
        if (Objects.nonNull(titleFilter)) {

            return showData.stream().filter(show -> show.getTitle().startsWith(titleFilter))
                    .collect(Collectors.toList());
        }

        return showData;
    }

}
