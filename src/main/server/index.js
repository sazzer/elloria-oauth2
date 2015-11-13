const express = require('express');
const bodyParser = require('body-parser');
const compression = require('compression');
const errorHandler = require('errorhandler');
const slash = require('express-slash');
const morgan = require('morgan');
const responseTime = require('response-time');

module.exports = () => {
    const app = express();
    app.enable('strict routing');
    
    const router = express.Router({
        caseSensitive: app.get('case sensitive routing'),
        strict: app.get('strict routing')
    });
    
    // Set up middleware
    app.use(bodyParser.json());
    app.use(bodyParser.raw());
    app.use(bodyParser.text());
    app.use(bodyParser.urlencoded());
    
    app.use(compression());
    app.use(errorHandler());
    app.use(responseTime());
    
    app.use(morgan('combined'));
    
    app.use(router);
    app.use(slash());
    
    // Set up routes
    require('./routes/debug')(router);
    return app;
}
