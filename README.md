# dir-reader

A simple example of reading the content file system over http server.

## Usage

For a quick test, fire up simple http server like:

    python -m SimpleHTTPServer

Then issue `lein run` with url:

    lein run http://localhost:8000/

!NOTE: Make sure url ends wiht "/".


## ToDo

Use core.async to fetch dirs in parallel.

## License

Copyright © 2013 Ales Kotnik

Distributed under the [WTFPL](http://en.wikipedia.org/wiki/WTFPL) (Do What the Fuck You Want to Public License).
