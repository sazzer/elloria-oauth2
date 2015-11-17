'use strict';

const expect = require('chai').expect;
const express = require('express');
const request = require('supertest');

describe('Scopes Routes', () => {
    let app;

    beforeEach(() => {
        const scopesRoutes = require('root-require')('src/main/server/routes/scopes');
        app = express();
        scopesRoutes(app);
    });

    describe('GET /api/scopes', () => {
        let response;

        beforeEach((cb) => {
            request(app)
                .get('/api/scopes')
                .end((err, res) => {
                    response = res;
                    cb();
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
            expect(response.body).to.include({scope: 'oauth2:admin'});
            expect(response.body).to.include({scope: 'oauth2:read'});
            expect(response.body).to.include({scope: 'oauth2:write'});
        });
    });

    describe('GET /api/scopes/:id', () => {
        let response;

        beforeEach((cb) => {
            request(app)
                .get('/api/scopes/oauth2:admin')
                .end((err, res) => {
                    response = res;
                    cb();
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
            expect(response.body).to.deep.equal({scope: 'oauth2:admin'});
        });
    });
});
