'use strict';

/**
 * Representation of a single Scope
 */
class Scope {
    /**
     * Construct the scope
     * @param {String} id The ID of the scope
     */
    constructor(id) {
        this._id = id;
    }

    /**
     * Get the ID of the scope
     * @return {String} the ID
     */
    get id() {
        return this._id;
    }
};

module.exports = Scope;
