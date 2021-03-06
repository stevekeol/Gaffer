/*
 * Copyright 2016 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.gchq.gaffer.spark.operation.scalardd;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;
import uk.gov.gchq.gaffer.data.element.Element;
import uk.gov.gchq.gaffer.data.element.id.DirectedType;
import uk.gov.gchq.gaffer.data.elementdefinition.view.View;
import uk.gov.gchq.gaffer.operation.Operation;
import uk.gov.gchq.gaffer.operation.Options;
import uk.gov.gchq.gaffer.operation.graph.GraphFilters;
import uk.gov.gchq.gaffer.operation.io.Output;
import uk.gov.gchq.gaffer.spark.serialisation.TypeReferenceSparkImpl;
import java.util.Map;

public class GetRDDOfAllElements implements
        Operation,
        Output<RDD<Element>>,
        GraphFilters,
        Rdd,
        Options {

    private Map<String, String> options;
    private SparkContext sparkContext;
    private View view;
    private DirectedType directedType;

    public GetRDDOfAllElements() {
    }

    public GetRDDOfAllElements(final SparkContext sparkContext) {
        setSparkContext(sparkContext);
    }

    @Override
    public Map<String, String> getOptions() {
        return options;
    }

    @Override
    public void setOptions(final Map<String, String> options) {
        this.options = options;
    }

    @Override
    public TypeReference<RDD<Element>> getOutputTypeReference() {
        return new TypeReferenceSparkImpl.RDDElement();
    }

    @Override
    public SparkContext getSparkContext() {
        return sparkContext;
    }

    @Override
    public void setSparkContext(final SparkContext sparkContext) {
        this.sparkContext = sparkContext;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setView(final View view) {
        this.view = view;
    }

    @Override
    public DirectedType getDirectedType() {
        return directedType;
    }

    @Override
    public void setDirectedType(final DirectedType directedType) {
        this.directedType = directedType;
    }

    public static class Builder extends Operation.BaseBuilder<GetRDDOfAllElements, Builder>
            implements Output.Builder<GetRDDOfAllElements, RDD<Element>, Builder>,
            GraphFilters.Builder<GetRDDOfAllElements, Builder>,
            Rdd.Builder<GetRDDOfAllElements, Builder>,
            Options.Builder<GetRDDOfAllElements, Builder> {
        public Builder() {
            super(new GetRDDOfAllElements());
        }
    }
}
