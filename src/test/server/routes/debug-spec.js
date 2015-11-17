'use strict';

const expect = require('chai').expect;
const express = require('express');
const request = require('supertest');
const mockery = require('mockery');
const sinon = require('sinon');
const moment = require('moment-timezone');

describe('Debug Routes', () => {
    let app;
    let mockMoment;

    before(() => {
        mockery.enable({
            warnOnReplace: false,
            warnOnUnregistered: false,
            useCleanCache: true
        });
    });

    after(() => {
        mockery.disable();
    });

    beforeEach(() => {
        mockMoment = sinon.stub().returns(moment('2014-06-01T12:00:00Z'));
        mockery.registerMock('moment-timezone', mockMoment);

        const debugRoutes = require('root-require')('src/main/server/routes/debug');
        app = express();
        debugRoutes(app);
    });

    describe('GET /api/debug/ping', () => {
        let response;

        beforeEach((cb) => {
            request(app)
                .get('/api/debug/ping')
                .end((err, res) => {
                    response = res;
                    cb(err);
                });
        });

        it('Returns a 200 OK', () => {
            expect(response.statusCode).to.equal(200);
        });
        it('Returns a content type of text/plain', () => {
            expect(response.type).to.equal('text/plain');
        });
        it('Returns the expected payload', () => {
            expect(response.text).to.equal('Pong');
        });
    });

    describe('GET /api/debug/now', () => {
        let response;

        beforeEach((cb) => {
            request(app)
                .get('/api/debug/now')
                .end((err, res) => {
                    response = res;
                    cb(err);
                });
        });

        it('Returns a 200 OK', () => {
            expect(response.statusCode).to.equal(200);
        });
        it('Returns a content type of text/plain', () => {
            expect(response.type).to.equal('text/plain');
        });
        it('Returns the expected payload', () => {
            expect(response.text).to.equal('2014-06-01T12:00:00+00:00');
        });
    });
});
