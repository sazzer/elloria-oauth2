'use strict';

const Router = require('express').Router;
const scopes = require('../../oauth2/scopes');

/**
 * Map a single scope from the internal definition to the API definition
 * @param {Scope} scope the scope to map
 * @return {Object} the API version of the scope
 */
function mapScope(scope) {
    return {
        scope: scope.id
    };
}

/**
 * Set up the Scopes routes
 * @param {Router} router the router to add the routes to
 */
module.exports = function(router) {
    const scopesRouter = Router();
    router.use('/api/scopes', scopesRouter);

    scopesRouter.get('/', (req, res) => {
        scopes.list()
            .then(scopes => scopes.map(mapScope))
            .then(scopes => res.send(scopes))
            .catch((err) => {
                res.send(500);
            });
    });

    scopesRouter.get('/:id', (req, res) => {
        scopes.get(req.params.id)
            .then(mapScope)
            .then(scope => res.send(scope))
            .catch((err) => {
                res.send(404);
            });
    });
}
