angular.module('map', ['leaflet', 'bridgeservice'])
    .controller('mapController', function ($scope, leaflet, bridgeservice) {

        $scope.updates = bridgeservice.updates;
        $scope.job = bridgeservice.job;

        $scope.bridgeName;
        $scope.selected;

        leaflet.drawMap();

        function redrawBridges() {

            var bounds = leaflet.getBounds();

            bridgeservice.readBridges(bounds).then(function (response) {
                console.log('Drawing bridges ...');
                leaflet.drawBridges(response.data, function (bridge) {
                    $scope.selected = bridge;
                    $scope.bridgeName = $scope.selected.name;
                    $scope.$digest();
                });
            });
        }

        leaflet.registerChangeListener(redrawBridges);

        redrawBridges();

        $scope.save = function () {
            bridgeservice.writeBridge($scope.selected);
            $scope.selected.name = $scope.bridgeName;
        };

        $scope.submit = function () {
            bridgeservice.submitJob();
        };

        $scope.status = function () {
            bridgeservice.checkStatus();
        };

        $scope.reset = function() {
            bridgeservice.reset();
            delete $scope.seleced;
            delete $scope.bridgeName;
        }

    });