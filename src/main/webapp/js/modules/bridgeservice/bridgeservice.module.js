angular.module('bridgeservice', [])

    .factory('bridgeservice', function ($http) {


        var _changeset;
        var _statuslog;

        function reset () {
            _changeset = {
                bridges: []
            };
            _statuslog = [];
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

            submitChangeSet: function (username, password) {

                if(_changeset.id) {
                    console.log('This changeset is already sent');
                    return;
                }

                var bridgesToSend = _changeset.bridges;

                return $http(
                    {
                        url: 'r/changeset/create',
                        method: 'POST',
                        headers: {
                            Accept: 'application/json',
                            'X-username': username,
                            'X-password': password
                        },
                        data: bridgesToSend
                    }).then(function (response) {
                        _changeset = response.data;
                        _statuslog = [{
                            time: Date.now(),
                            status: response.data.status
                        }];
                        return _changeset;
                    });

            },

            writeBridge: function (bridge) {

                _changeset.bridges.push(bridge);

            },

            checkStatus: function (username, password) {

                if(!_changeset.id) return;

                return $http(
                    {
                        url: 'r/changeset/',
                        method: 'GET',
                        headers: {
                            Accept: 'application/json',
                            'X-username': username,
                            'X-password': password
                        }
                    }).then(function (response) {
                        _changeset = response.data;
                        _statuslog.push({
                            time: Date.now(),
                            status: response.data.status
                        });
                        return _changeset;
                    });

            },

            changeset: function () {
                return _changeset;
            },

            statuslog : function () {
                return _statuslog;
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

