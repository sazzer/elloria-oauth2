'use strict';

const Scope = require('./scope');

const SCOPES = [
    new Scope('oauth2:admin'),
    new Scope('oauth2:read'),
    new Scope('oauth2:write')
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
