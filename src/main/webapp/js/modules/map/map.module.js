angular.module('map', ['leaflet', 'bridgeservice'])
    .controller('mapController', function ($scope, leaflet, bridgeservice) {

        $scope.selected;

        leaflet.drawMap();

        function redrawBridges() {
            var bounds = leaflet.getBounds();
            bridgeservice.readBridges(bounds).then(function (response) {
                console.log('Drawing bridges ...');
                leaflet.drawBridges(response.data, function (bridge) {
                    $scope.selected = bridge;
                    $scope.$digest();
                });
            });
        }

        leaflet.registerChangeListener(redrawBridges);

        redrawBridges();

        $scope.save = function () {
            bridgeservice.updateBridge($scope.selected);
        };

        $scope.status = function () {
            bridgeservice.checkStatus()
                .then(function (response) {
                    $scope.jobs = response.data;
                });
        };

    });