package com.karthik.springbootgraphql.datafetchers;

import java.util.List;

import com.netflix.dgs.codgen.generated.client.ShowsGraphQLQuery;
import com.netflix.dgs.codgen.generated.client.ShowsProjectionRoot;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { DgsAutoConfiguration.class, ShowDataFetcher.class })
public class ShowDataFetcherTest {

    @Autowired
    DgsQueryExecutor queryExecutor;

    @Test
    void shows() {

        var queryRequest = new GraphQLQueryRequest(ShowsGraphQLQuery.newRequest().titleFilter("Dare").build(),
                new ShowsProjectionRoot().title());

        String query = queryRequest.serialize();
        List<String> titles = queryExecutor.executeAndExtractJsonPath(query, "data.shows[*].title");
        Assertions.assertThat(titles).containsExactly("DareDevil");

    }

}
