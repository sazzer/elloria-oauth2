'use strict';

const expect = require('chai').expect;
const express = require('express');
const request = require('supertest-as-promised');
const debugRoutes = require('root-require')('src/main/server/routes/debug');

describe('Debug Routes', () => {
    let app;
    beforeEach(() => {
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
});
