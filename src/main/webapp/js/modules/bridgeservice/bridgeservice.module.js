angular.module('bridgeservice', [])

    .factory('bridgeservice', function ($http) {


        var _job;

        function reset () {
            _job = {
                bridges: []
            };
        }
        reset();


        return {

            readBridges: function (bounds) {

                return $http(
                    {
                        url: 'r/bridge/read',
                        method: 'POST',
                        headers: {Accept: 'application/json'},
                        data: bounds
                    }
                );

            },

            submitJob: function () {

                if(_job.id) {
                    console.log('This job is already sent');
                    return;
                }

                var bridgesToSend = _job.bridges;

                return $http(
                    {
                        url: 'r/bridge/update',
                        method: 'POST',
                        headers: {Accept: 'application/json'},
                        data: bridgesToSend
                    }).then(function (response) {
                        _job = response.data;
                        _job.lastStatusCheck = Date.now();
                        return _job;
                    });

            },

            writeBridge: function (bridge) {

                _job.bridges.push(bridge);

            },

            checkStatus: function () {

                if(!_job.id) return;

                return $http(
                    {
                        url: 'r/job/',
                        method: 'POST',
                        headers: {Accept: 'application/json'}
                    }).then(function (response) {
                        _job = response.data;
                        _job.lastStatusCheck = Date.now();
                        return _job;
                    });

            },

            job: function () {
                return _job;
            },

            reset: reset


        }

    })

    // Show spinner when loading data
    .config(function ($httpProvider) {
        $httpProvider.interceptors.push(function ($rootScope) {
            return {
                'request': function (config) {
                    $rootScope.loading = true;
                    return config;
                },

                'response': function (response) {
                    $rootScope.loading = false;
                    return response;
                },

                'responseError': function (rejection) {
                    $rootScope.loading = false;
                    return rejection;
                }

            };
        });
    });

