package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Represents a {@code DrawNumberApp} implementation.
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param views
     *              the views to attach
     */
    public DrawNumberApp(final DrawNumberView... views) {
        /*
         * Side-effect proof
         */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view : views) {
            view.setObserver(this);
            view.start();
        }
        /*
         * Loading of configuration from file.
         */
        final InputStream in = Objects.requireNonNull(
                ClassLoader.getSystemResourceAsStream("config.yml"));
        final var configBuilder = new Configuration.Builder();
        try (var br = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = br.readLine()) != null) { // NOPMD
                final StringTokenizer tk = new StringTokenizer(line);
                final String pattern = tk.nextToken();
                if ("minimum:".equals(pattern)) {
                    configBuilder.min(Integer.parseInt(tk.nextToken()));
                }
                if ("maximum:".equals(pattern)) {
                    configBuilder.max(Integer.parseInt(tk.nextToken()));
                }
                if ("attempts:".equals(pattern)) {
                    configBuilder.attempts(Integer.parseInt(tk.nextToken()));
                }
            }
        } catch (final IOException e) {
            this.displayError(e.getMessage());
        }
        final Configuration config = configBuilder.build();
        if (config.isConsistent()) {
            this.model = new DrawNumberImpl(config);
        } else {
            /*
             * using default values for config
             */
            this.model = new DrawNumberImpl(new Configuration.Builder().build());
        }
    }

    private void displayError(final String message) {
        for (final var view : this.views) {
            view.displayError(message);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view : this.views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : this.views) {
                view.numberIncorrect();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetGame() {
        this.model.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *             ignored
     * @throws FileNotFoundException
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp(new DrawNumberViewImpl(), 
            new PrintStreamView(System.out), 
            new PrintStreamView("output.log"));
    }
}
