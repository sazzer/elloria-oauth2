const Router = require('express').Router;

/**
 * Set up the Scopes routes
 * @param {Router} router the router to add the routes to
 */
module.exports = function(router) {
    const scopesRouter = Router();
    router.use('/api/scopes', scopesRouter);

    scopesRouter.get('/', (req, res) => {
        res.send([
            {
                scope: 'oauth2:admin'
            }, {
                scope: 'oauth2:read'
            }, {
                scope: 'oauth2:write'
            }
        ]);
    });

    scopesRouter.get('/:id', (req, res) => {
        res.send({
            scope: req.params.id
        });
    });
}
