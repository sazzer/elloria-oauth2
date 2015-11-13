"use strict";

const gulp = require('gulp');
const server = require('gulp-develop-server');
const eslint = require('gulp-eslint');
const mocha = require('gulp-mocha');
const istanbul = require('gulp-istanbul');
const esdoc = require('gulp-esdoc');
const gutil = require('gulp-util');
const cucumber = require('gulp-cucumber');

mocha.watched = false;

gulp.task('lint:main', () => {
    return gulp.src('src/main/**/*.js')
        .pipe(eslint({
            configFile: './eslintrc'
        }))
        .pipe(eslint.format())
        .pipe(eslint.failAfterError());
});

gulp.task('lint:unit-test', () => {
    return gulp.src('src/test/**/*.js')
        .pipe(eslint({
            configFile: './eslintrc'
        }))
        .pipe(eslint.format())
        .pipe(eslint.failAfterError());
});

gulp.task('pre-unit-test', () => {
    return gulp.src('src/main/**/*.js')
        .pipe(istanbul({
            includeUntested: true
        }))
        .pipe(istanbul.hookRequire());
});

gulp.task('unit-test', ['lint:unit-test', 'pre-unit-test'], () => {
    return gulp.src('src/test/**/*-spec.js')
        .pipe(mocha({
            ui: 'bdd',
            growl: 'true'
        }))
        .on(mocha.watched ? 'error' : 'ignoreme', function(err) {
          gutil.log(err);
          if (mocha.watched) {
            this.emit('end');
          } else {
            process.exit(1);
          }
        })
        .pipe(istanbul.writeReports({
            dir: './target/coverage',
            reporters: [
                'lcov',
                'html',
                'json',
                'text',
                'text-summary'
            ],
            reportOpts: {
                dir: './target/coverage'
            }
        }));
});

gulp.task('lint:integration-test', () => {
    return gulp.src('src/cucumber/**/*.js')
        .pipe(eslint({
            configFile: './eslintrc'
        }))
        .pipe(eslint.format())
        .pipe(eslint.failAfterError());
});

gulp.task('integration-test', ['lint:integration-test'], () => {
    return gulp.src('src/cucumber/features/**/*')
        .pipe(cucumber({
            steps: 'src/cucumber/steps/**/*.js',
            support: 'src/cucumber/support/**/*.js',
            tags: ['~@ignore']
        }));
});

gulp.task('doc', () => {
    return gulp.src('src/main')
        .pipe(esdoc({
            destination: './target/docs',
            test: {
                type: 'mocha',
                source: './src/test'
            },
            autoPrivate: true,
            unexportIdentifier: true,
            undocumentIdentifier: true,
            buildinExternal: true,
            coverage: true,
            includeSource: true,
            lint: true
        }));
});

gulp.task('build', ['lint:main', 'doc', 'unit-test']);
gulp.task('test', ['build', 'unit-test', 'integration-test']);

gulp.task('start', ['build'], () => {
    server.listen({path: './src/main/index.js'});
});

gulp.task('watch:start', ['start'], () => {
    gulp.watch('src/**/*', ['start', server.restart]);
});

gulp.task('watch:build', ['build'], () => {
    mocha.watched = true;
    gulp.watch('src/**/*', ['build']);
});

gulp.task('default', ['build']);
