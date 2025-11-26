import { Graph, sampleData, validateGraphData, buildGraph, getNearbyCities } from "./graph.js";

//tests for validateGraphData
test('tests for validateGraphData', () => {
    //cities and edges are not arrays
    expect(validateGraphData({cities: 2, edges: 3})).toStrictEqual({ok: false, reason: "cities/edges must be arrays"});

    //one of the city names being the number 2
    expect(validateGraphData({cities: ["a", 2], edges: [1,2]})).toStrictEqual({ok: false, reason: "invalid city entry"});

    //edge references to invalid city name 
    expect(validateGraphData({cities: ["a", "asd2r44"], edges: [1,2]})).toStrictEqual({ok: false, reason: "edge references unknown city"});

    //"invalid distance -1"
    expect(validateGraphData({cities: ["a", "b"], edges: [{"from": "a", "to": "b", "distance": -1}]})).toStrictEqual({ok: false, reason: "invalid distance"});

    //correct data cities a & b, edge with distance 10
    expect(validateGraphData({cities: ["a", "b"], edges: [{"from": "a", "to": "b", "distance": 10}]})).toStrictEqual({ok: true});

});

//tests for buildGraph
test('tests for buildGraph', () => {
    //invalid city
    expect(() => buildGraph([{"to": "adsa", "distance": 10}, {"to": "sdasda", "distance": 10}], [{}])).toThrow(Error);

    //edges contain unknown cities
    expect(() => buildGraph([{"to": "ads34242a", "distance": 10}, {"to": "sdasda", "distance": 20}], [{"from": "x", "to": "y", "distance": 50}])).toThrow(Error);

    //edge has invalid distance
    expect(() => buildGraph([{"to": "x", "distance": 10}, {"to": "y", "distance": 20}], [{"from": "x", "to": "y", "distance": -12}])).toThrow(Error);

    //valid data should return Graph g
    const cities = ["A", "B"];
    const edges = [{ from: "A", to: "B", distance: 5 }];
    const g = buildGraph(cities, edges);

    expect(g).toBeInstanceOf(Graph);
    expect(g.adj.has("A")).toBe(true);
    expect(g.adj.has("B")).toBe(true);
    expect(g.neighbors("A")).toEqual([{ to: "B", distance: 5 }]);
});

test('tests for getNearbyCities', () => {
    const graph = new Graph();
    graph.addCity("A");
    //Invalid graph
    expect(() => {getNearbyCities({}, ["A"])}).toThrow(Error);

    graph.addCity("B");
    graph.addCity("C");

    graph.addEdge("A", "B", 10);
    graph.addEdge("A", "C", 20);

    //valid data
    expect(getNearbyCities(graph, "A", 30)).toStrictEqual([{city: "B", distance: 10}, {city: "C", distance: 20}]);
});