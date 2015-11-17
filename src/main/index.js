'use strict';

/* istanbul ignore next */
(() => {
    const express = require('./server');
    const log = require('./log');

    const app = express();

    const server = app.listen(3000, function () {
        const host = server.address().address;
        const port = server.address().port;

        log.info({host, port}, 'Example app listening at http://%s:%s', host, port);
    });
})();
