'use strict';

const expect = require('chai').expect;
const express = require('express');
const request = require('supertest');
const mockery = require('mockery');
const sinon = require('sinon');

describe('Scopes Routes', () => {
    let app;
    let mockScopes;

    before(() => {
        mockery.enable({
            warnOnReplace: false,
            warnOnUnregistered: false,
            useCleanCache: true
        });

        mockScopes = {
            list: sinon.stub(),
            get: sinon.stub()
        };
        mockery.registerMock('../../oauth2/scopes', mockScopes);

        const scopesRoutes = require('root-require')('src/main/server/routes/scopes');
        app = express();
        scopesRoutes(app);
    });

    after(() => {
        mockery.disable();
    });

    describe('GET /api/scopes', () => {
        let response;

        before((cb) => {
            mockScopes.list.returns(Promise.resolve([
                {
                    id: 'oauth2:admin',
                    namespace: 'oauth2',
                    scope: 'admin'
                }, {
                    id: 'oauth2:read',
                    namespace: 'oauth2',
                    scope: 'read'
                }, {
                    id: 'oauth2:write',
                    namespace: 'oauth2',
                    scope: 'write'
                }
            ]));

            request(app)
                .get('/api/scopes')
                .end((err, res) => {
                    response = res;
                    cb(err);
                });
        });

        it('Returns a 200 OK', () => {
            expect(response.statusCode).to.equal(200);
        });
        it('Returns a content type of application/json', () => {
            expect(response.type).to.equal('application/json');
        });
        it('Returns an array', () => {
            expect(response.body).to.be.an.array;
        });
        it('Returns an array of the expected values', () => {
            expect(response.body).to.have.length(3);
            expect(response.body).to.include({id: 'oauth2:admin', namespace: 'oauth2', scope: 'admin'});
            expect(response.body).to.include({id: 'oauth2:read', namespace: 'oauth2', scope: 'read'});
            expect(response.body).to.include({id: 'oauth2:write', namespace: 'oauth2', scope: 'write'});
        });
    });

    describe('GET /api/scopes/:id', () => {
        let response;

        before((cb) => {
            mockScopes.get.withArgs('oauth2:admin')
                .returns(Promise.resolve({
                    id: 'oauth2:admin'
                }));

            request(app)
                .get('/api/scopes/oauth2:admin')
                .end((err, res) => {
                    response = res;
                    cb(err);
                });
        });

        it('Returns a 200 OK', () => {
            expect(response.statusCode).to.equal(200);
        });
        it('Returns a content type of application/json', () => {
            expect(response.type).to.equal('application/json');
        });
        it('Returns an object', () => {
            expect(response.body).to.be.an.object;
        });
        it('Returns the expected values', () => {
            expect(response.body).to.deep.equal({id: 'oauth2:admin'});
        });
    });
});
