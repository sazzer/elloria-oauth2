'use strict';

const Scope = require('./scope');

const SCOPES = [
    new Scope({scope: 'noop'}),
    new Scope({namespace: 'oauth2', scope: 'admin'}),
    new Scope({namespace: 'oauth2', scope: 'read'}),
    new Scope({namespace: 'oauth2', scope: 'write'})
];

/**
 * List all of the known scopes
 * @return {Promise} A promise for the known scopes
 */
function listScopes() {
    return Promise.resolve(SCOPES);
}

/**
 * Get a single scope with the given ID
 * @param {String} id the ID of the scope
 * @return {Promise} A promise for the scope
 */
function getScope(id) {
    return new Promise((resolve, reject) => {
        const scope = SCOPES.find(scope => id === scope.id);

        if (scope) {
            resolve(scope);
        } else {
            reject();
        }
    });
}

module.exports = {
    list: listScopes,
    get: getScope
};
