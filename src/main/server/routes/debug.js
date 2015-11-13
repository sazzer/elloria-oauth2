/**
 * Set up the Debug routes
 * @param {Router} router the router to add the routes to
 */
module.exports = function(router) {
    router.get('/api/debug/ping', (req, res) => {
        res.set('Content-Type', 'text/plain');
        res.send('Pong');
    });
}
