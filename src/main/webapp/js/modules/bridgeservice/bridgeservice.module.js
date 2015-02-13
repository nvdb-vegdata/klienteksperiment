angular.module('bridgeservice', [] )

    .factory('bridgeservice', function($http) {

        return {

            readBridges: function(bounds) {

                return $http(
                    {
                        url: 'r/bridge',
                        method: 'POST',
                        headers: {Accept: 'application/json'},
                        data: bounds
                    }
                );

            }
        }

    });