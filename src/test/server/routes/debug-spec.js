'use strict';

const expect = require('chai').expect;
const express = require('express');
const request = require('supertest-as-promised');
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
        
        beforeEach(() => {
            response = request(app)
                .get('/api/debug/ping');
        });
            
        it('Returns a 200 OK', () => {
            return response.expect(200);
        });
        it('Returns a content type of text/plain', () => {
            return response.expect('Content-Type', /text\/plain/);
        });
        it('Returns the expected payload', () => {
            return response.expect('Pong');
        });
    });
    
    describe('GET /api/debug/now', () => {
        let response;
        
        beforeEach(() => {
            response = request(app)
                .get('/api/debug/now');
        });
            
        it('Returns a 200 OK', () => {
            return response.expect(200);
        });
        it('Returns a content type of text/plain', () => {
            return response.expect('Content-Type', /text\/plain/);
        });
        it('Returns the expected payload', () => {
            return response.expect('2014-06-01T12:00:00+00:00');
        });
    });
});
