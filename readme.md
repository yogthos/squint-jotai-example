# Game-of-life example

- Install dependencies:

    npm install

- Run `npx squint watch` to start compiling `.cljs` -> `.js`
- Run `npx vite --config=vite.config.mjs public` to start a webserver and to hot-reload your project!

## Babashka tasks
To run both tasks above using one command with `bb dev`. See [bb.edn](bb.edn).

## Production

To build your production website:

    npx vite build public

