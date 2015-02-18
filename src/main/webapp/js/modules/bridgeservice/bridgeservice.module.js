angular.module('bridgeservice', [])

    .factory('bridgeservice', function ($http) {

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

            updateBridge: function (bridge) {

                $http(
                    {
                        url: 'r/bridge/update',
                        method: 'POST',
                        headers: {Accept: 'application/json'},
                        data: bridge
                    });

            },

            checkStatus: function () {

                return $http(
                    {
                        url: 'r/job',
                        method: 'POST',
                        headers: {Accept: 'application/json'}
                    });

            }

        }

    });