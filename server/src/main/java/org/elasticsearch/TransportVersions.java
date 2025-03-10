/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the "Elastic License
 * 2.0", the "GNU Affero General Public License v3.0 only", and the "Server Side
 * Public License v 1"; you may not use this file except in compliance with, at
 * your election, the "Elastic License 2.0", the "GNU Affero General Public
 * License v3.0 only", or the "Server Side Public License, v 1".
 */

package org.elasticsearch;

import org.elasticsearch.core.Assertions;
import org.elasticsearch.core.UpdateForV9;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.IntFunction;

/**
 * <p>Transport version is used to coordinate compatible wire protocol communication between nodes, at a fine-grained level.  This replaces
 * and supersedes the old Version constants.</p>
 *
 * <p>Before adding a new version constant, please read the block comment at the end of the list of constants.</p>
 */
public class TransportVersions {

    /*
     * NOTE: IntelliJ lies!
     * This map is used during class construction, referenced by the registerTransportVersion method.
     * When all the transport version constants have been registered, the map is cleared & never touched again.
     */
    static TreeSet<Integer> IDS = new TreeSet<>();

    static TransportVersion def(int id) {
        if (IDS == null) throw new IllegalStateException("The IDS map needs to be present to call this method");

        if (IDS.add(id) == false) {
            throw new IllegalArgumentException("Version id " + id + " defined twice");
        }
        if (id < IDS.last()) {
            throw new IllegalArgumentException("Version id " + id + " is not defined in the right location. Keep constants sorted");
        }
        return new TransportVersion(id);
    }

    @UpdateForV9(owner = UpdateForV9.Owner.CORE_INFRA) // remove the transport versions with which v9 will not need to interact
    public static final TransportVersion ZERO = def(0);
    public static final TransportVersion V_7_0_0 = def(7_00_00_99);
    public static final TransportVersion V_7_0_1 = def(7_00_01_99);
    public static final TransportVersion V_7_1_0 = def(7_01_00_99);
    public static final TransportVersion V_7_2_0 = def(7_02_00_99);
    public static final TransportVersion V_7_2_1 = def(7_02_01_99);
    public static final TransportVersion V_7_3_0 = def(7_03_00_99);
    public static final TransportVersion V_7_3_2 = def(7_03_02_99);
    public static final TransportVersion V_7_4_0 = def(7_04_00_99);
    public static final TransportVersion V_7_5_0 = def(7_05_00_99);
    public static final TransportVersion V_7_6_0 = def(7_06_00_99);
    public static final TransportVersion V_7_7_0 = def(7_07_00_99);
    public static final TransportVersion V_7_8_0 = def(7_08_00_99);
    public static final TransportVersion V_7_8_1 = def(7_08_01_99);
    public static final TransportVersion V_7_9_0 = def(7_09_00_99);
    public static final TransportVersion V_7_10_0 = def(7_10_00_99);
    public static final TransportVersion V_7_10_1 = def(7_10_01_99);
    public static final TransportVersion V_7_11_0 = def(7_11_00_99);
    public static final TransportVersion V_7_12_0 = def(7_12_00_99);
    public static final TransportVersion V_7_13_0 = def(7_13_00_99);
    public static final TransportVersion V_7_14_0 = def(7_14_00_99);
    public static final TransportVersion V_7_15_0 = def(7_15_00_99);
    public static final TransportVersion V_7_15_1 = def(7_15_01_99);
    public static final TransportVersion V_7_16_0 = def(7_16_00_99);
    public static final TransportVersion V_7_17_0 = def(7_17_00_99);
    public static final TransportVersion V_7_17_1 = def(7_17_01_99);
    public static final TransportVersion V_7_17_8 = def(7_17_08_99);
    public static final TransportVersion V_8_0_0 = def(8_00_00_99);
    public static final TransportVersion V_8_1_0 = def(8_01_00_99);
    public static final TransportVersion V_8_2_0 = def(8_02_00_99);
    public static final TransportVersion V_8_3_0 = def(8_03_00_99);
    public static final TransportVersion V_8_4_0 = def(8_04_00_99);
    public static final TransportVersion V_8_5_0 = def(8_05_00_99);
    public static final TransportVersion V_8_6_0 = def(8_06_00_99);
    public static final TransportVersion V_8_6_1 = def(8_06_01_99);
    public static final TransportVersion V_8_7_0 = def(8_07_00_99);
    public static final TransportVersion V_8_7_1 = def(8_07_01_99);
    public static final TransportVersion V_8_8_0 = def(8_08_00_99);
    public static final TransportVersion V_8_8_1 = def(8_08_01_99);
    /*
     * READ THE COMMENT BELOW THIS BLOCK OF DECLARATIONS BEFORE ADDING NEW TRANSPORT VERSIONS
     * Detached transport versions added below here.
     */
    public static final TransportVersion V_8_9_X = def(8_500_020);
    public static final TransportVersion V_8_10_X = def(8_500_061);
    public static final TransportVersion V_8_11_X = def(8_512_00_1);
    public static final TransportVersion V_8_12_0 = def(8_560_00_0);
    public static final TransportVersion V_8_12_1 = def(8_560_00_1);
    public static final TransportVersion V_8_13_0 = def(8_595_00_0);
    public static final TransportVersion V_8_13_4 = def(8_595_00_1);
    public static final TransportVersion V_8_14_0 = def(8_636_00_1);
    public static final TransportVersion V_8_15_0 = def(8_702_00_2);
    public static final TransportVersion V_8_15_2 = def(8_702_00_3);
    public static final TransportVersion QUERY_RULES_LIST_INCLUDES_TYPES_BACKPORT_8_15 = def(8_702_00_4);
    public static final TransportVersion ML_INFERENCE_DONT_DELETE_WHEN_SEMANTIC_TEXT_EXISTS = def(8_703_00_0);
    public static final TransportVersion INFERENCE_ADAPTIVE_ALLOCATIONS = def(8_704_00_0);
    public static final TransportVersion INDEX_REQUEST_UPDATE_BY_SCRIPT_ORIGIN = def(8_705_00_0);
    public static final TransportVersion ML_INFERENCE_COHERE_UNUSED_RERANK_SETTINGS_REMOVED = def(8_706_00_0);
    public static final TransportVersion ENRICH_CACHE_STATS_SIZE_ADDED = def(8_707_00_0);
    public static final TransportVersion ENTERPRISE_GEOIP_DOWNLOADER = def(8_708_00_0);
    public static final TransportVersion NODES_STATS_ENUM_SET = def(8_709_00_0);
    public static final TransportVersion MASTER_NODE_METRICS = def(8_710_00_0);
    public static final TransportVersion SEGMENT_LEVEL_FIELDS_STATS = def(8_711_00_0);
    public static final TransportVersion ML_ADD_DETECTION_RULE_PARAMS = def(8_712_00_0);
    public static final TransportVersion FIX_VECTOR_SIMILARITY_INNER_HITS = def(8_713_00_0);
    public static final TransportVersion INDEX_REQUEST_UPDATE_BY_DOC_ORIGIN = def(8_714_00_0);
    public static final TransportVersion ESQL_ATTRIBUTE_CACHED_SERIALIZATION = def(8_715_00_0);
    public static final TransportVersion REGISTER_SLM_STATS = def(8_716_00_0);
    public static final TransportVersion ESQL_NESTED_UNSUPPORTED = def(8_717_00_0);
    public static final TransportVersion ESQL_SINGLE_VALUE_QUERY_SOURCE = def(8_718_00_0);
    public static final TransportVersion ESQL_ORIGINAL_INDICES = def(8_719_00_0);
    public static final TransportVersion ML_INFERENCE_EIS_INTEGRATION_ADDED = def(8_720_00_0);
    public static final TransportVersion INGEST_PIPELINE_EXCEPTION_ADDED = def(8_721_00_0);
    public static final TransportVersion ZDT_NANOS_SUPPORT_BROKEN = def(8_722_00_0);
    public static final TransportVersion REMOVE_GLOBAL_RETENTION_FROM_TEMPLATES = def(8_723_00_0);
    public static final TransportVersion RANDOM_RERANKER_RETRIEVER = def(8_724_00_0);
    public static final TransportVersion ESQL_PROFILE_SLEEPS = def(8_725_00_0);
    public static final TransportVersion ZDT_NANOS_SUPPORT = def(8_726_00_0);
    public static final TransportVersion LTR_SERVERLESS_RELEASE = def(8_727_00_0);
    public static final TransportVersion ALLOW_PARTIAL_SEARCH_RESULTS_IN_PIT = def(8_728_00_0);
    public static final TransportVersion RANK_DOCS_RETRIEVER = def(8_729_00_0);
    public static final TransportVersion ESQL_ES_FIELD_CACHED_SERIALIZATION = def(8_730_00_0);
    public static final TransportVersion ADD_MANAGE_ROLES_PRIVILEGE = def(8_731_00_0);
    public static final TransportVersion REPOSITORIES_TELEMETRY = def(8_732_00_0);
    public static final TransportVersion ML_INFERENCE_ALIBABACLOUD_SEARCH_ADDED = def(8_733_00_0);
    public static final TransportVersion FIELD_CAPS_RESPONSE_INDEX_MODE = def(8_734_00_0);
    public static final TransportVersion GET_DATA_STREAMS_VERBOSE = def(8_735_00_0);
    public static final TransportVersion ESQL_ADD_INDEX_MODE_CONCRETE_INDICES = def(8_736_00_0);
    public static final TransportVersion UNASSIGNED_PRIMARY_COUNT_ON_CLUSTER_HEALTH = def(8_737_00_0);
    public static final TransportVersion ESQL_AGGREGATE_EXEC_TRACKS_INTERMEDIATE_ATTRS = def(8_738_00_0);
    public static final TransportVersion CCS_TELEMETRY_STATS = def(8_739_00_0);
    public static final TransportVersion GLOBAL_RETENTION_TELEMETRY = def(8_740_00_0);
    public static final TransportVersion ROUTING_TABLE_VERSION_REMOVED = def(8_741_00_0);
    public static final TransportVersion ML_SCHEDULED_EVENT_TIME_SHIFT_CONFIGURATION = def(8_742_00_0);
    public static final TransportVersion SIMULATE_COMPONENT_TEMPLATES_SUBSTITUTIONS = def(8_743_00_0);
    public static final TransportVersion ML_INFERENCE_IBM_WATSONX_EMBEDDINGS_ADDED = def(8_744_00_0);
    public static final TransportVersion BULK_INCREMENTAL_STATE = def(8_745_00_0);
    public static final TransportVersion FAILURE_STORE_STATUS_IN_INDEX_RESPONSE = def(8_746_00_0);
    public static final TransportVersion ESQL_AGGREGATION_OPERATOR_STATUS_FINISH_NANOS = def(8_747_00_0);
    public static final TransportVersion ML_TELEMETRY_MEMORY_ADDED = def(8_748_00_0);
    public static final TransportVersion ILM_ADD_SEARCHABLE_SNAPSHOT_TOTAL_SHARDS_PER_NODE = def(8_749_00_0);
    public static final TransportVersion SEMANTIC_TEXT_SEARCH_INFERENCE_ID = def(8_750_00_0);
    public static final TransportVersion ML_INFERENCE_CHUNKING_SETTINGS = def(8_751_00_0);
    public static final TransportVersion SEMANTIC_QUERY_INNER_HITS = def(8_752_00_0);
    public static final TransportVersion RETAIN_ILM_STEP_INFO = def(8_753_00_0);
    public static final TransportVersion ADD_DATA_STREAM_OPTIONS = def(8_754_00_0);
    public static final TransportVersion CCS_REMOTE_TELEMETRY_STATS = def(8_755_00_0);
    public static final TransportVersion ESQL_CCS_EXECUTION_INFO = def(8_756_00_0);
    public static final TransportVersion REGEX_AND_RANGE_INTERVAL_QUERIES = def(8_757_00_0);
    public static final TransportVersion RRF_QUERY_REWRITE = def(8_758_00_0);
    public static final TransportVersion SEARCH_FAILURE_STATS = def(8_759_00_0);
    public static final TransportVersion INGEST_GEO_DATABASE_PROVIDERS = def(8_760_00_0);
    public static final TransportVersion DATE_TIME_DOC_VALUES_LOCALES = def(8_761_00_0);
    public static final TransportVersion FAST_REFRESH_RCO = def(8_762_00_0);
    public static final TransportVersion TEXT_SIMILARITY_RERANKER_QUERY_REWRITE = def(8_763_00_0);
    public static final TransportVersion SIMULATE_INDEX_TEMPLATES_SUBSTITUTIONS = def(8_764_00_0);
    public static final TransportVersion RETRIEVERS_TELEMETRY_ADDED = def(8_765_00_0);
    public static final TransportVersion ESQL_CACHED_STRING_SERIALIZATION = def(8_766_00_0);
    public static final TransportVersion CHUNK_SENTENCE_OVERLAP_SETTING_ADDED = def(8_767_00_0);
    public static final TransportVersion OPT_IN_ESQL_CCS_EXECUTION_INFO = def(8_768_00_0);
    public static final TransportVersion QUERY_RULE_TEST_API = def(8_769_00_0);
    public static final TransportVersion ESQL_PER_AGGREGATE_FILTER = def(8_770_00_0);
    public static final TransportVersion ML_INFERENCE_ATTACH_TO_EXISTSING_DEPLOYMENT = def(8_771_00_0);
    public static final TransportVersion CONVERT_FAILURE_STORE_OPTIONS_TO_SELECTOR_OPTIONS_INTERNALLY = def(8_772_00_0);
    public static final TransportVersion INFERENCE_DONT_PERSIST_ON_READ_BACKPORT_8_16 = def(8_772_00_1);
    public static final TransportVersion ADD_COMPATIBILITY_VERSIONS_TO_NODE_INFO_BACKPORT_8_16 = def(8_772_00_2);
    public static final TransportVersion SKIP_INNER_HITS_SEARCH_SOURCE_BACKPORT_8_16 = def(8_772_00_3);
    public static final TransportVersion QUERY_RULES_LIST_INCLUDES_TYPES_BACKPORT_8_16 = def(8_772_00_4);
    public static final TransportVersion REMOVE_MIN_COMPATIBLE_SHARD_NODE = def(8_773_00_0);
    public static final TransportVersion REVERT_REMOVE_MIN_COMPATIBLE_SHARD_NODE = def(8_774_00_0);
    public static final TransportVersion ESQL_FIELD_ATTRIBUTE_PARENT_SIMPLIFIED = def(8_775_00_0);
    public static final TransportVersion INFERENCE_DONT_PERSIST_ON_READ = def(8_776_00_0);
    public static final TransportVersion SIMULATE_MAPPING_ADDITION = def(8_777_00_0);
    public static final TransportVersion INTRODUCE_ALL_APPLICABLE_SELECTOR = def(8_778_00_0);
    public static final TransportVersion INDEX_MODE_LOOKUP = def(8_779_00_0);
    public static final TransportVersion INDEX_REQUEST_REMOVE_METERING = def(8_780_00_0);
    public static final TransportVersion CPU_STAT_STRING_PARSING = def(8_781_00_0);
    public static final TransportVersion QUERY_RULES_RETRIEVER = def(8_782_00_0);
    public static final TransportVersion ESQL_CCS_EXEC_INFO_WITH_FAILURES = def(8_783_00_0);
    public static final TransportVersion LOGSDB_TELEMETRY = def(8_784_00_0);
    public static final TransportVersion LOGSDB_TELEMETRY_STATS = def(8_785_00_0);
    public static final TransportVersion KQL_QUERY_ADDED = def(8_786_00_0);
    public static final TransportVersion ROLE_MONITOR_STATS = def(8_787_00_0);
    public static final TransportVersion DATA_STREAM_INDEX_VERSION_DEPRECATION_CHECK = def(8_788_00_0);
    public static final TransportVersion ADD_COMPATIBILITY_VERSIONS_TO_NODE_INFO = def(8_789_00_0);
    public static final TransportVersion VERTEX_AI_INPUT_TYPE_ADDED = def(8_790_00_0);
    public static final TransportVersion SKIP_INNER_HITS_SEARCH_SOURCE = def(8_791_00_0);
    public static final TransportVersion QUERY_RULES_LIST_INCLUDES_TYPES = def(8_792_00_0);
    public static final TransportVersion INDEX_STATS_ADDITIONAL_FIELDS = def(8_793_00_0);
    public static final TransportVersion INDEX_STATS_ADDITIONAL_FIELDS_REVERT = def(8_794_00_0);
    public static final TransportVersion FAST_REFRESH_RCO_2 = def(8_795_00_0);
    public static final TransportVersion ESQL_ENRICH_RUNTIME_WARNINGS = def(8_796_00_0);
    public static final TransportVersion INGEST_PIPELINE_CONFIGURATION_AS_MAP = def(8_797_00_0);
    public static final TransportVersion LOGSDB_TELEMETRY_CUSTOM_CUTOFF_DATE_FIX_8_17 = def(8_797_00_1);
    public static final TransportVersion SOURCE_MODE_TELEMETRY_FIX_8_17 = def(8_797_00_2);
    public static final TransportVersion INDEXING_PRESSURE_THROTTLING_STATS = def(8_798_00_0);
    public static final TransportVersion REINDEX_DATA_STREAMS = def(8_799_00_0);
    public static final TransportVersion ESQL_REMOVE_NODE_LEVEL_PLAN = def(8_800_00_0);
    public static final TransportVersion LOGSDB_TELEMETRY_CUSTOM_CUTOFF_DATE = def(8_801_00_0);
    public static final TransportVersion SOURCE_MODE_TELEMETRY = def(8_802_00_0);

    /*
     * STOP! READ THIS FIRST! No, really,
     *        ____ _____ ___  ____  _        ____  _____    _    ____    _____ _   _ ___ ____    _____ ___ ____  ____ _____ _
     *       / ___|_   _/ _ \|  _ \| |      |  _ \| ____|  / \  |  _ \  |_   _| | | |_ _/ ___|  |  ___|_ _|  _ \/ ___|_   _| |
     *       \___ \ | || | | | |_) | |      | |_) |  _|   / _ \ | | | |   | | | |_| || |\___ \  | |_   | || |_) \___ \ | | | |
     *        ___) || || |_| |  __/|_|      |  _ <| |___ / ___ \| |_| |   | | |  _  || | ___) | |  _|  | ||  _ < ___) || | |_|
     *       |____/ |_| \___/|_|   (_)      |_| \_\_____/_/   \_\____/    |_| |_| |_|___|____/  |_|   |___|_| \_\____/ |_| (_)
     *
     * A new transport version should be added EVERY TIME a change is made to the serialization protocol of one or more classes. Each
     * transport version should only be used in a single merged commit (apart from the BwC versions copied from o.e.Version, ≤V_8_8_1).
     *
     * ADDING A TRANSPORT VERSION
     * To add a new transport version, add a new constant at the bottom of the list, above this comment. Don't add other lines,
     * comments, etc. The version id has the following layout:
     *
     * M_NNN_SS_P
     *
     * M - The major version of Elasticsearch
     * NNN - The server version part
     * SS - The serverless version part. It should always be 00 here, it is used by serverless only.
     * P - The patch version part
     *
     * To determine the id of the next TransportVersion constant, do the following:
     * - Use the same major version, unless bumping majors
     * - Bump the server version part by 1, unless creating a patch version
     * - Leave the serverless part as 00
     * - Bump the patch part if creating a patch version
     *
     * If a patch version is created, it should be placed sorted among the other existing constants.
     *
     * REVERTING A TRANSPORT VERSION
     *
     * If you revert a commit with a transport version change, you MUST ensure there is a NEW transport version representing the reverted
     * change. DO NOT let the transport version go backwards, it must ALWAYS be incremented.
     *
     * DETERMINING TRANSPORT VERSIONS FROM GIT HISTORY
     *
     * If your git checkout has the expected minor-version-numbered branches and the expected release-version tags then you can find the
     * transport versions known by a particular release ...
     *
     *     git show v8.11.0:server/src/main/java/org/elasticsearch/TransportVersions.java | grep '= def'
     *
     * ... or by a particular branch ...
     *
     *     git show 8.11:server/src/main/java/org/elasticsearch/TransportVersions.java | grep '= def'
     *
     * ... and you can see which versions were added in between two versions too ...
     *
     *     git diff v8.11.0..main -- server/src/main/java/org/elasticsearch/TransportVersions.java
     *
     * In branches 8.7-8.10 see server/src/main/java/org/elasticsearch/TransportVersion.java for the equivalent definitions.
     */

    /**
     * Reference to the earliest compatible transport version to this version of the codebase.
     * This should be the transport version used by the highest minor version of the previous major.
     */
    @UpdateForV9(owner = UpdateForV9.Owner.CORE_INFRA)
    // This needs to be bumped to the 8.last
    public static final TransportVersion MINIMUM_COMPATIBLE = V_7_17_0;

    /**
     * Reference to the minimum transport version that can be used with CCS.
     * This should be the transport version used by the previous minor release.
     */
    public static final TransportVersion MINIMUM_CCS_VERSION = V_8_15_0;

    static final NavigableMap<Integer, TransportVersion> VERSION_IDS = getAllVersionIds(TransportVersions.class);

    // the highest transport version constant defined in this file, used as a fallback for TransportVersion.current()
    static final TransportVersion LATEST_DEFINED;
    static {
        LATEST_DEFINED = VERSION_IDS.lastEntry().getValue();

        // see comment on IDS field
        // now we're registered all the transport versions, we can clear the map
        IDS = null;
    }

    public static NavigableMap<Integer, TransportVersion> getAllVersionIds(Class<?> cls) {
        Map<Integer, String> versionIdFields = new HashMap<>();
        NavigableMap<Integer, TransportVersion> builder = new TreeMap<>();

        Set<String> ignore = Set.of("ZERO", "CURRENT", "MINIMUM_COMPATIBLE", "MINIMUM_CCS_VERSION");

        for (Field declaredField : cls.getFields()) {
            if (declaredField.getType().equals(TransportVersion.class)) {
                String fieldName = declaredField.getName();
                if (ignore.contains(fieldName)) {
                    continue;
                }

                TransportVersion version;
                try {
                    version = (TransportVersion) declaredField.get(null);
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                }
                builder.put(version.id(), version);

                if (Assertions.ENABLED) {
                    // check the version number is unique
                    var sameVersionNumber = versionIdFields.put(version.id(), fieldName);
                    assert sameVersionNumber == null
                        : "Versions ["
                            + sameVersionNumber
                            + "] and ["
                            + fieldName
                            + "] have the same version number ["
                            + version.id()
                            + "]. Each TransportVersion should have a different version number";
                }
            }
        }

        return Collections.unmodifiableNavigableMap(builder);
    }

    static Collection<TransportVersion> getAllVersions() {
        return VERSION_IDS.values();
    }

    static final IntFunction<String> VERSION_LOOKUP = ReleaseVersions.generateVersionsLookup(TransportVersions.class, LATEST_DEFINED.id());

    // no instance
    private TransportVersions() {}
}
