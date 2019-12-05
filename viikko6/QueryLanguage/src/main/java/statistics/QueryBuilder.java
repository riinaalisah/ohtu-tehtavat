package statistics;

import statistics.matcher.*;

import java.util.Stack;

public class QueryBuilder {

    private Stack stack;

    public QueryBuilder() {
        this.stack = new Stack();
    }

    public QueryBuilder playsIn(String team) {
        this.stack.push(new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String fieldName) {
        this.stack.push(new HasAtLeast(value, fieldName));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String fieldName) {
        this.stack.push(new HasFewerThan(value, fieldName));
        return this;
    }

    public QueryBuilder oneOf(Matcher... matchers) {
        this.stack.push(new Or(matchers));
        return this;
    }

    public Matcher build() {
        Matcher[] matchers = new Matcher[stack.size()];
        for (int i = 0; i < matchers.length; i++) {
            matchers[i] = (Matcher) stack.pop();
        }
        return new And(matchers);
    }
}
