const moment = require('moment-timezone');
const Router = require('express').Router;

/**
 * Set up the Debug routes
 * @param {Router} router the router to add the routes to
 */
module.exports = function(router) {
    const debugRouter = Router();
    router.use('/api/debug', debugRouter);
    
    debugRouter.get('/ping', (req, res) => {
        res.set('Content-Type', 'text/plain');
        res.send('Pong');
    });
    
    debugRouter.get('/now', (req, res) => {
        const now = moment().tz('UTC');
        
        res.set('Content-Type', 'text/plain');
        res.send(now.format());
    });
}
