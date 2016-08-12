/**
 * 
 */
var flattenObject = function(ob) {
	var toReturn = {};
	
	for (var i in ob) {
		if (!ob.hasOwnProperty(i)) continue;
		
		if ((typeof ob[i]) == 'object') {
			var flatObject = flattenObject(ob[i]);
			for (var x in flatObject) {
				if (!flatObject.hasOwnProperty(x)) continue;
				
				toReturn[i + '.' + x] = flatObject[x];
			}
		} else {
			toReturn[i] = ob[i];
		}
	}
	
	return toReturn;
};



var xmlJsonApp = angular.module("xmlJsonApp",[]);
xmlJsonApp.controller("xmlController", ["$scope","$http",function($scope,$http) {
	$http.get("sample.xml")
	.success(function(data, status, headers, config) {
		var x2js = new X2JS();
		var jsonOutput = x2js.xml_str2json(data);
		var originalJson = angular.toJson(jsonOutput,true);
		//console.log(jsonOutput);
		//console.log(originalJson);
		$scope.fields = jsonOutput.data.related.root;
		console.log($scope.fields);
		//console.log(angular.toJson(flattenObject(jsonOutput),true));
		$scope.datas = flattenObject(jsonOutput);
		//$scope.fields = flattenObject(jsonOutput);
		var arr = $scope.datas;
		
		angular.forEach($scope.fields, function(value, key) {
			console.log(key+ " "+" "+key+ " "+value);
		});
		
		  
		  
	})
	.error(function(data, status, headers, config) {
		console.log("There is a problem.");
	})
	
	$scope.getTypeOf = function(value) {
		if(value === null) {
	        return "null";
	    }
	    if(Array.isArray(value)) {
	        return "array";
	    }
	    return typeof value; 
	};
}]);

xmlJsonApp.directive('dynamicElement', function() {
    function link(scope, element) {
    	//console.log(scope);
        
        //console.log(scope.elements);
        
        angular.forEach(scope.elements, function(value, key) {
            //console.log(" "+value);
            // create an element based on its type
            if(value != null) {
            	//console.log(value);
            }
            /*
            var newElement = document.createElement(value.elements);
            // add the content
            newElement.innerHTML = value.content;
            
            for (attribute in value) {
                console.log(attribute);
                if (attribute != 'type' && attribute != 'content') {
                    //apply attribute
                    newElement.setAttribute(attribute, value[attribute]);
                }
            }
            element.append(newElement);*/
        });
    };
    
    return {
        restrict: 'EA',
        scope: {
            elements : '='
        },
        link: link
    };
})
;