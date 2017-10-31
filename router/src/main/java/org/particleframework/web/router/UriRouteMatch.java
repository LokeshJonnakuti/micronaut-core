/*
 * Copyright 2017 original authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.particleframework.web.router;

import org.particleframework.http.HttpMethod;
import org.particleframework.http.MediaType;
import org.particleframework.http.uri.UriMatchInfo;
import org.particleframework.core.type.Argument;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Graeme Rocher
 * @since 1.0
 */
public interface UriRouteMatch<R> extends UriMatchInfo, RouteMatch<R> {

    /**
     * <p>Returns the required arguments for this RouteMatch</p>
     *
     * <p>Note that this is not the save as {@link #getArguments()} as it will include a subset of the arguments excluding those that have been subtracted from the URI variables</p>
     *
     * @return The required arguments in order to invoke this route
     */
    default List<Argument> getRequiredArguments() {
        Map<String, Object> matchVariables = getVariables();
        return Arrays.stream(getArguments())
                .filter((arg) -> !matchVariables.containsKey(arg.getName()))
                .collect(Collectors.toList());
    }

    /**
     * @return The matched HTTP method
     */
    HttpMethod getHttpMethod();

    @Override
    UriRouteMatch<R> fulfill(Map<String, Object> argumentValues);

    @Override
    UriRouteMatch<R> decorate(Function<RouteMatch<R>, R> executor);

    /**
     * Whether the specified content type is an accepted type
     *
     * @param contentType The content type
     * @return True if it is
     */
    boolean accept(MediaType contentType);
}
