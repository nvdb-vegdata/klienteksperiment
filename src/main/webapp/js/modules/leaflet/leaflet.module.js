angular.module('leaflet', [])

    .factory('leaflet', function () {

        var _crs = new L.Proj.CRS('EPSG:25833', '+proj=utm +zone=33 +ellps=GRS80 +units=m +no_defs ',
            {
                origin: [-2500000.0, 9045984.0],
                resolutions: [
                    21674.7100160867,
                    10837.35500804335,
                    5418.677504021675,
                    2709.3387520108377,
                    1354.6693760054188,
                    677.3346880027094,
                    338.6673440013547,
                    169.33367200067735,
                    84.66683600033868,
                    42.33341800016934,
                    21.16670900008467,
                    10.583354500042335,
                    5.291677250021167,
                    2.6458386250105836,
                    1.3229193125052918,
                    0.6614596562526459,
                    0.33072982812632296
                ]
            });

        var _map = L.map('map', {
            crs: _crs,
            continuousWorld: true,
            worldCopyJump: false
        });

        var _bridgeLayer = L.layerGroup([]).addTo(_map);

        function drawMap() {

            var backgroundMap = new L.tileLayer('http://m{s}.nvdbcache.geodataonline.no/arcgis/rest/services/Trafikkportalen/GeocacheTrafikkJPG/MapServer/tile/{z}/{y}/{x}', {
                maxZoom: 16,
                minZoom: 0,
                subdomains: '123456789',
                continuousWorld: true,
                attribution: 'NVDB'
            });

            _map.addLayer(backgroundMap);
            _map.setView([63.43, 10.40], 8);

        }

        function getBounds() {

            var bounds = _map.getBounds();
            return {
                southWest: _crs.project(bounds._southWest),
                northEast: _crs.project(bounds._northEast)
            }

        }

        function drawBridges(bridges, selectBridge) {

            _bridgeLayer.clearLayers();

            bridges.forEach(function (bridge) {
                bridge.lines.forEach(function (line) {

                    var points = [];
                    line.points.forEach(function (point) {
                        var p = _crs.projection.unproject(L.point(point.x, point.y));
                        points.push(p)
                    });

                    var polyline = L.polyline(points, {color: 'blue'});
                    polyline.on('click', function() {
                        selectBridge(bridge);
                    });
                    _bridgeLayer.addLayer(polyline);

                });
            });

        }


        function registerChangeListener(onChange) {

            _map.on('moveend', onChange);

        }


        return {

            drawMap: drawMap,
            getBounds: getBounds,
            drawBridges: drawBridges,
            registerChangeListener: registerChangeListener

        }

    });