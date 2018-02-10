package java_base.w03.s05;

class Step08 {
    public static void main(String[] args) {
        String[] keywords = {"spam", "bad"};
        int maxLength = 42;

        TextAnalyzer[] analyzer = {
                new SpamAnalyzer(keywords),
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(maxLength)
        };

        String normalComment = "Hello, it is the normal comment";
        String spamComment = "Hello, it is the spam comment";
        String negativeComment = "Hello, it is the negative comment =(";
        String longComment = "Hello, it is the toooooooooooooooooo long comment";

        System.out.println(checkLabels(analyzer, normalComment));
        System.out.println(checkLabels(analyzer, spamComment));
        System.out.println(checkLabels(analyzer, negativeComment));
        System.out.println(checkLabels(analyzer, longComment));
    }

    public static Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer next : analyzers) {
            if (next.processText(text) != Label.OK) {
                return next.processText(text);
            }
        }

        return Label.OK;
    }

    static abstract class KeywordAnalyzer implements TextAnalyzer {
        protected abstract String[] getKeywords();
        protected abstract Label getLabel();

        @Override
        public Label processText(String text) {
            for (String next : getKeywords()) {
                if (text.contains(next)) {
                    return getLabel();
                }
            }

            return Label.OK;
        }
    }

    static class SpamAnalyzer extends KeywordAnalyzer {
        private String[] keywords;

        public SpamAnalyzer(String... keywords) {
            this.keywords = keywords;
        }

        @Override
        protected String[] getKeywords() {
            return keywords;
        }

        @Override
        protected Label getLabel() {
            return Label.SPAM;
        }
    }

    static class NegativeTextAnalyzer extends KeywordAnalyzer {
        private String[] keywords = {":(", "=(", ":|"};

        @Override
        protected String[] getKeywords() {
            return keywords;
        }

        @Override
        protected Label getLabel() {
            return Label.NEGATIVE_TEXT;
        }
    }

    static class TooLongTextAnalyzer implements TextAnalyzer {
        private int maxLength;

        public TooLongTextAnalyzer(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public Label processText(String text) {
            return (text.length() > maxLength ? Label.TOO_LONG : Label.OK);
        }
    }
}
