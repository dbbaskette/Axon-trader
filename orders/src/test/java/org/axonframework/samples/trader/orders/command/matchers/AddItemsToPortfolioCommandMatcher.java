/*
 * Copyright (c) 2010-2012. Axon Framework
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

package org.axonframework.samples.trader.orders.command.matchers;

import org.axonframework.samples.trader.api.orders.trades.OrderBookId;
import org.axonframework.samples.trader.api.orders.trades.PortfolioId;
import org.axonframework.samples.trader.api.portfolio.stock.AddItemsToPortfolioCommand;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * @author Jettro Coenradie
 */
public class AddItemsToPortfolioCommandMatcher extends BaseCommandMatcher<AddItemsToPortfolioCommand> {

    private OrderBookId orderBookIdentifier;
    private PortfolioId portfolioIdentifier;
    private long amountOfItemsToAdd;

    private AddItemsToPortfolioCommandMatcher(PortfolioId portfolioIdentifier,
                                              OrderBookId orderBookIdentifier, long amountOfItemsToAdd) {
        this.amountOfItemsToAdd = amountOfItemsToAdd;
        this.portfolioIdentifier = portfolioIdentifier;
        this.orderBookIdentifier = orderBookIdentifier;
    }

    public static Matcher newInstance(PortfolioId portfolioIdentifier,
                                      OrderBookId orderBookIdentifier, long amountOfItemsToAdd) {
        return new AddItemsToPortfolioCommandMatcher(portfolioIdentifier, orderBookIdentifier, amountOfItemsToAdd);
    }
    @Override
    protected boolean doMatches(AddItemsToPortfolioCommand command) {
        return command.getOrderBookId().equals(orderBookIdentifier)
                && command.getPortfolioId().equals(portfolioIdentifier)
                && command.getAmountOfItemsToAdd() == amountOfItemsToAdd;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("AddItemsToPortfolioCommand with amountOfItemsToAdd [")
                .appendValue(amountOfItemsToAdd)
                .appendText("] for OrderBook with identifier [")
                .appendValue(orderBookIdentifier)
                .appendText("] and for Portfolio with identifier [")
                .appendValue(portfolioIdentifier)
                .appendText("]");
    }
}
