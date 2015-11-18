'use strict';

/**
 * Representation of a single Scope
 */
class Scope {
    /**
     * Construct the scope
     * @param {Object} options The build options
     * @param {String} options.namespace The Namespace of the scope
     * @param {String} options.scope The ID of the scope
     */
    constructor(options) {
        this._namespace = options.namespace || null;
        this._scope = options.scope;
    }

    /**
     * Get the namespace of the Scope
     * @return {String} the namespace
     */
    get namespace() {
        return this._namespace;
    }

    /**
     * Get the name of the Scope inside it's namespace
     * @return {String} the name of the scope
     */
    get scope() {
        return this._scope;
    }

    /**
     * Get the ID of the scope
     * @return {String} the ID
     */
    get id() {
        let id;
        if (this._namespace !== null) {
            id = this._namespace + ':' + this._scope;
        } else {
            id = this._scope;
        }
        return id;
    }
};

module.exports = Scope;
